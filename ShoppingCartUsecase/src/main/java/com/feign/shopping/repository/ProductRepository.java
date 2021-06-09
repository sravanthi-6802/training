package com.feign.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feign.shopping.repository.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
