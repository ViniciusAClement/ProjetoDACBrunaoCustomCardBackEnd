package com.bcc.cca.security;

import java.util.List;

import com.bcc.cca.entites.Admin;
import com.bcc.cca.entites.Client;
import com.bcc.cca.repositories.AdminRepository;
import com.bcc.cca.repositories.ClientRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CcaUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    public CcaUserDetailsService(AdminRepository adminRepository, ClientRepository clientRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username).orElse(null);
        if (admin != null) {
            return new AuthUser(
                    admin.getId(),
                    admin.getEmail(),
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        Client client = clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new AuthUser(
                client.getId(),
                client.getEmail(),
                client.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
        );
    }
}
