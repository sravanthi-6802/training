package com.feign.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feign.shopping.dto.ProductDto;
import com.feign.shopping.repository.ProductRepository;
import com.feign.shopping.repository.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductDto addProduct(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		Product result = productRepository.save(product);
		BeanUtils.copyProperties(result, productDto);
		return productDto;
	}
	
	public List<Product> retrieveProducts(){
		return productRepository.findAll();
	}
	
	public Product retrieveProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}
	
	public void deleteProducts() {
		productRepository.deleteAll();
	}
	
	public Product updateProduct(ProductDto productDto) {
		//productDto.getId();
		/*
		 * if(productRepository.findById(productDto.getId())) {
		 * 
		 * }
		 */
		return null;
	}

}
