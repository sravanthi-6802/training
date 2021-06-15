package com.feign.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feign.shopping.dto.ProductDto;
import com.feign.shopping.exception.CustomEntityNotFoundException;
import com.feign.shopping.repository.ProductRepository;
import com.feign.shopping.repository.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return productRepository.save(product);
	}

	public Product retrieveProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		return null;
	}

	public void deleteProducts() {
		productRepository.deleteAll();
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> getProductByBrandAndColor(String brand, String color) throws CustomEntityNotFoundException {
		List<Product> product = productRepository.findAllByBrandAndColor(brand, color);
		if (product != null) {
			return product;
		} else {
			throw new CustomEntityNotFoundException("Product Not Found");
		}

	}

	public List<Product> getProductByBrand(String brand) {
		List<Product> product = productRepository.findAllByBrand(brand);
		if (product != null) {
			return product;
		} else {
			throw new CustomEntityNotFoundException("Product Not Found");
		}

	}

	public List<Product> getAllProductByName(String name) {
		List<Product> product = productRepository.findAllByProductName(name);
		if (product != null) {
			return product;
		} else {
			throw new CustomEntityNotFoundException("Product Not Found");
		}
	}

}
