package com.bcc.cca.dto.response;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {

    private String token;

    private String type = "Bearer";

    private Instant expiresAt;

    private String role;

    private Long userId;

    private String email;
}
