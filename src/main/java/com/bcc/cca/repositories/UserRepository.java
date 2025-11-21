package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
