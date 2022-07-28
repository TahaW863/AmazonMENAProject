package com.taha.auth.auth.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taha.auth.auth.interfaces.UserService;
import com.taha.auth.auth.services.JwtService;
import com.taha.auth.auth.services.UserConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
    private final UserService userConnectService;

    public AuthController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.objectMapper = new ObjectMapper();
        this.userConnectService = userService;
    }
    @GetMapping("/auth")
    public ResponseEntity<Void> auth() {
        log.info("auth called");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/health")
    public ResponseEntity<Void> health() {
        log.info("Health check");
        return ResponseEntity.ok().build();
    }
    /*
    @GetMapping("/refresh")
    public ResponseEntity<ResponseData> refresh(HttpServletRequest request) {
        log.info("Refresh request with IP: {}", request.getRemoteAddr());
        String refresh_token = request.getHeader(AUTHORIZATION);
        if (Objects.nonNull(refresh_token) && refresh_token.startsWith("Bearer ")) {
            try {
                log.info("Refresh token is valid");
                refresh_token = refresh_token.substring(7);
                Optional<DecodedJWT> jwt = jwtService.verify(refresh_token);
                if (jwt.isPresent()) {
                    DecodedJWT decodedJWT = jwt.get();
                    String username = decodedJWT.getClaim("username").asString();
                    User user = userConnectService.getUser(username).orElseThrow(() -> new RuntimeException("User not found"));
                    Optional<String> accessToken = jwtService.getAccessToken(user.getUsername(),
                            objectMapper.writeValueAsString(user.getRoles().stream().map(Roles::toString).toList())
                    );
                    Optional<String> refreshToken = jwtService.getRefreshToken(user.getUsername(),
                            objectMapper.writeValueAsString(user.getRoles().stream().map(Roles::toString).toList())
                    );
                    if (accessToken.isPresent() && refreshToken.isPresent()) {
                        return ResponseEntity.ok(ResponseData.builder()
                                .message("Success")
                                .data(List.of(Token
                                        .builder()
                                        .accessToken(accessToken.get())
                                        .refreshToken(refreshToken.get())
                                        .build()))
                                .build()
                        );
                    }
                }
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                log.error("Error while refreshing token: {}", e.getMessage());
                return ResponseEntity.badRequest().build();
            }
        } else {
            log.error("Invalid refresh token");
            return ResponseEntity.badRequest().build();
        }
    }*/
}
