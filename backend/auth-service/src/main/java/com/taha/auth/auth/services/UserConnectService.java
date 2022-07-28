package com.taha.auth.auth.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taha.auth.auth.interfaces.UserService;
import com.taha.auth.auth.models.Customer;
import com.taha.auth.utils.responses.model.BackEndResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConnectService implements UserService, UserDetailsService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Customer getUserById(String id) {
        log.info("getUserById: {}", id);
        return restTemplate.getForObject("http://localhost:8080/api/v1/customers/" + id, Customer.class);
    }

    @Override
    public Customer getUserByEmail(String email) {
        try{
            log.info("getUserByEmail: {}", email);
            String link="http://localhost:8080/api/v1/customers/email?email=" + email;
            log.info("link: {}", link);
            Customer customer = objectMapper.readValue(objectMapper.writeValueAsString(restTemplate.getForEntity(link, BackEndResponse.class).getBody().getData()), Customer.class);
            log.info("customer: {}", customer);
            return customer;
        } catch (Exception e) {
            log.error("getUserByEmail: {}", e.getMessage());
        } return null;
    }


    @Override
    public void saveUser(Customer customer) {
        log.info("saveUser: {}", customer);
        restTemplate.postForObject("http://localhost:8080/api/v1/customers", List.of(customer), List.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", email);
        Customer customer= getUserByEmail(email);
        Collection<SimpleGrantedAuthority> authorities = customer.getRole().stream()
                .map(role -> new SimpleGrantedAuthority( role)).toList();
        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                authorities);
    }
}
