package com.taha.auth.auth.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String accessToken;
    private String refreshToken;
}
