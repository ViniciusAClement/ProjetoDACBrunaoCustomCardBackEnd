package com.bcc.cca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Admin> findByEmail(String email);
}
