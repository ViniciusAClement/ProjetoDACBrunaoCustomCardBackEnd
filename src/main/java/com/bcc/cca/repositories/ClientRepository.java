package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

}
