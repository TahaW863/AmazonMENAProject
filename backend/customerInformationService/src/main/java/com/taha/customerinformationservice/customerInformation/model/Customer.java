package com.taha.customerinformationservice.customerInformation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
    @NonNull
    @Min(value = 9, message = "Password must be at least 9 characters long")
    @Max(value = 100, message = "Password must be at most 100 characters long")
    private String password;
    @NonNull
    private Address address;
    @NonNull
    private String phone;
    private String token;
    private LocalDateTime tokenCreatedAt;
    @NonNull
    private List<String> role;
    private List<String> channelIds;
}
