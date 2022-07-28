package com.taha.auth.auth.configuration;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Configuration
@AllArgsConstructor
public class JWTConfiguration {
    private JWTBinds jwt;
}

