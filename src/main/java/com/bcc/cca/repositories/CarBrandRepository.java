package com.bcc.cca.repositories;

import com.bcc.cca.entites.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.CarBrand;

import java.util.Set;

public interface CarBrandRepository  extends JpaRepository<CarBrand, Long>{

    boolean existsByName(String name);

    CarBrand car(Set<Car> car);
}
