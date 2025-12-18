package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
