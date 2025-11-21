package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
