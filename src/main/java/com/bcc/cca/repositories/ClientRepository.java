package com.bcc.cca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Client> findByEmail(String email);
}
