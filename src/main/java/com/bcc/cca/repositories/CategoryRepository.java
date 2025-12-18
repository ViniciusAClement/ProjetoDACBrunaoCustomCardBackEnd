package com.bcc.cca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcc.cca.entites.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

    boolean existsByName(String name);
}
