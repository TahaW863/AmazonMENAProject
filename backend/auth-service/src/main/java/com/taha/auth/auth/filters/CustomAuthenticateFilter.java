package com.taha.auth.auth.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taha.auth.auth.configuration.JWTConfiguration;
import com.taha.auth.auth.dtos.Token;
import com.taha.auth.auth.interfaces.UserService;
import com.taha.auth.auth.models.Customer;
import com.taha.auth.auth.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    public static final String DEFAULT_ADMIN = "default-admin";
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public CustomAuthenticateFilter(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder bCryptPasswordEncoder,  JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = new ObjectMapper();
        this.jwtService = jwtService;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.info("Attempting authentication");
            Customer user = objectMapper.readValue(request.getInputStream(), Customer.class);
            log.info("User: {}", user);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            log.info("Authentication : {}", authentication);
            return authentication;
        } catch (IOException e) {
            log.error("Error while attempting authentication", e);
            throw new AuthenticationException(e.getMessage()) {
                @Override
                public String getMessage() {
                    return e.getMessage();
                }
            };
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Successful authentication");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        Optional<String> accessToken = jwtService.getAccessToken(user.getUsername(),
                objectMapper.writeValueAsString(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        );
        Optional<String> refreshToken = jwtService.getRefreshToken(user.getUsername(),
                objectMapper.writeValueAsString(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        );
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (accessToken.isPresent() && refreshToken.isPresent()) {
            log.info("Successful authentication with access token and refresh token");
            if (user.getUsername().compareTo(DEFAULT_ADMIN) == 0) {
                Customer userOptional = userService.getUserByEmail(user.getUsername());
                String message = "";
                if (bCryptPasswordEncoder.matches(DEFAULT_ADMIN, userOptional.getPassword())) {
                    message = userOptional.getId().toString();
                    response.setStatus(HttpStatus.MULTI_STATUS.value());
                } else {
                    message = "Success";
                    response.setStatus(HttpServletResponse.SC_OK);
                }
                response.getWriter().write(objectMapper.writeValueAsString(
                        Token.builder()
                                .accessToken(accessToken.get())
                                .refreshToken(refreshToken.get())
                                .build()
                ));
                return;
            }

            response.getWriter().write(objectMapper.writeValueAsString(
                    Token.builder()
                    .accessToken(accessToken.get())
                    .refreshToken(refreshToken.get())
                    .build()));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().flush();
        } else {
            log.error("Error while generating tokens");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("Error while attempting authentication", failed);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.getWriter().write(objectMapper.writeValueAsString(Map.of("message", failed.getMessage())));
    }
}
