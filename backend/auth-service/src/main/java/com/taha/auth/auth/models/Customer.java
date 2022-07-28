package com.taha.auth.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address address;
    private String phone;
    private String token;
    private LocalDateTime tokenCreatedAt;
    private List<String> role;
    private List<String> channelIds;
}
