package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Long>{

}
