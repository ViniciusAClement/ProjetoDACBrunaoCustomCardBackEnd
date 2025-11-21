package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{

}
