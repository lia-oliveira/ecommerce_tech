package com.oliveiralia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveiralia.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
