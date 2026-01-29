package com.bcc.cca.resources;

import java.util.Optional;

import com.bcc.cca.dto.request.LoginRequestDTO;
import com.bcc.cca.dto.response.LoginResponseDTO;
import com.bcc.cca.security.AuthUser;
import com.bcc.cca.security.JwtService;
import com.bcc.cca.security.JwtService.TokenResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResource(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        AuthUser user = (AuthUser) authentication.getPrincipal();

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_CLIENT");

        String type = role.equals("ROLE_ADMIN") ? "ADMIN" : "CLIENT";

        TokenResult tokenResult = jwtService.generateToken(user.getUsername(), user.getId(), role, type);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(tokenResult.token());
        response.setExpiresAt(tokenResult.expiresAt());
        response.setRole(role);
        response.setUserId(user.getId());
        response.setEmail(user.getUsername());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
