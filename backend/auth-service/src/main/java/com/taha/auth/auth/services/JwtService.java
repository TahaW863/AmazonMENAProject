package com.taha.auth.auth.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.taha.auth.auth.configuration.JWTConfiguration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Getter
@Slf4j
public class JwtService {
    private final Algorithm algorithm;
    private JWTConfiguration jwtConfiguration;

    public JwtService(JWTConfiguration jwtConfiguration) {
       this.jwtConfiguration = jwtConfiguration;

        this.algorithm = Algorithm.HMAC256(this.jwtConfiguration.getJwt().getSecret());

        log.info("JwtService initialized: {}", this.jwtConfiguration.getJwt());
    }

    public Optional<DecodedJWT> verify(String token) throws JWTVerificationException {
        log.info("Verifying token: {}", token);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return Optional.of(verifier.verify(token));
    }

    public Optional<String> getAccessToken(String userName, String roles) {
        try {
            return Optional.of(com.auth0.jwt.JWT.create()
                    .withSubject(userName)
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfiguration.getJwt().getExpiration()))
                    .withIssuer(jwtConfiguration.getJwt().getIssuer())
                    .withClaim("username", userName)
                    .withClaim("roles", roles)
                    .sign(this.algorithm));
        } catch (Exception e) {
            log.error("Error while generating token: {}", userName);
        }
        return Optional.empty();
    }

    public Optional<String> getRefreshToken(String userName, String roles) {
        try {
            return Optional.of(com.auth0.jwt.JWT.create()
                    .withSubject(userName)
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfiguration.getJwt().getExpiration() * 2))
                    .withIssuer(jwtConfiguration.getJwt().getIssuer())
                    .withClaim("username", userName)
                    .withClaim("roles", roles)
                    .sign(this.algorithm));
        } catch (Exception e) {
            log.error("Error while generating token: {}", userName);
        }
        return Optional.empty();
    }
}
