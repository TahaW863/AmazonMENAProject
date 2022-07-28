package com.taha.auth.auth.filters;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taha.auth.auth.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Slf4j
public class CustomAuthorizeFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private final ObjectMapper objectMapper;
    public CustomAuthorizeFilter() {
        //this.jwtService = new JwtService();
        this.objectMapper = new ObjectMapper();
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Authorize filter");
        if (request.getServletPath().matches("/api/v1/auth/.*") || request.getServletPath().matches("/api/v1/discovery/.*")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader(AUTHORIZATION);
            if (Objects.nonNull(token) && token.startsWith("Bearer ")) {
                try {
                    log.info("Token is valid");
                    token = token.substring(7);
                    Optional<DecodedJWT> decodedJWTOptional = jwtService.verify(token);
                    if ( decodedJWTOptional.isPresent()){
                        DecodedJWT jwt= decodedJWTOptional.get();
                        String username = jwt.getClaim("username").asString();
                        String s= jwt.getClaim("roles").toString().replace("\\","");

                        List<String> roles=(List<String>) objectMapper.readValue(s.substring(1,s.length()-1), List.class);


                        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities =
                                roles.stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .toList();
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    }
                } catch (Exception e) {
                    log.error("Error while verifying token");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                log.error("Error token is null");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

    }
}
