package com.taha.customerinformationservice.customerInformation.services;

import com.taha.customerinformationservice.customerInformation.interfaces.CustomerRepository;
import com.taha.customerinformationservice.customerInformation.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Page<Customer>> getCustomers(int page, int size) {
        log.info("Getting customers with page: {} and size: {}", page, size);
        return Optional.of(customerRepository.findAll(PageRequest.of(page, size)));
    }
    public Optional<Customer> getCustomer(String id) {
        log.info("Getting customer with id: {}", id);
        return customerRepository.findById(id);
    }
    public void saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        customerRepository.save(customer);
    }
    public void deleteCustomer(String id) {
        log.info("Deleting customer with id: {}", id);
        customerRepository.deleteById(id);
    }
    public void saveCustomers(List<Customer> customers){
        log.info("Saving customers: {}", customers);
        customerRepository.saveAll(customers);
    }
    public Optional<Customer> getCustomerByEmail(String email) {
        log.info("Getting customer with email: {}", email);
        return customerRepository.findByEmail(email);
    }
}
