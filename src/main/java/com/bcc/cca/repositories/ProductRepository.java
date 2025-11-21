package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
