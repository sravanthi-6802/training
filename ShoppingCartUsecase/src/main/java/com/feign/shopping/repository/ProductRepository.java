package com.feign.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feign.shopping.repository.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByBrand(String brand);
	
	List<Product> findAllByBrandAndColor(String brand, String color);

	List<Product> findAllByProductName(String name);

}
