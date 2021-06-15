package com.feign.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feign.shopping.dto.ProductDto;
import com.feign.shopping.feignclient.FundTransferClient;
import com.feign.shopping.repository.entity.Product;
import com.feign.shopping.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private FundTransferClient fundTransferClient;

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	Environment environment;

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
		Product result = productService.addProduct(productDto);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Product>> retrieveProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(productService.retrieveProductById(id), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> removeProducts() {
		productService.deleteProducts();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/product/brand/{brand}")
	public ResponseEntity<List<Product>> searchByBrand(@PathVariable("brand") String brand) {
		return new ResponseEntity<>(productService.getProductByBrand(brand), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
		return new ResponseEntity<>(productService.getAllProductByName(name), HttpStatus.OK);
	}

	@GetMapping("/product/brand/color/{brand}/{color}")
	public ResponseEntity<List<Product>> searchByBrandAndColor(@PathVariable("brand") String brand,
			@PathVariable("color") String color) {
		return new ResponseEntity<>(productService.getProductByBrandAndColor(brand, color), HttpStatus.OK);
	}

	@GetMapping("/data")
	public String productData() {
		String port = environment.getProperty("local.server.port");
		return "Product Service Test Data running on " + port;
	}

	@GetMapping("/fundtransfer")
	public String fundTransferData() {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(() -> fundTransferClient.getUserData(),
				Throwable -> getFundTransferDowntimeResponse());
	}

	public String getFundTransferDowntimeResponse() {
		return "Fund transfer service is down, try after some time.";
	}
}
