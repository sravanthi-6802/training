package com.feign.shopping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feign.shopping.dto.ProductDto;
import com.feign.shopping.repository.entity.Product;
import com.feign.shopping.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	static ProductDto productDto;

	static Product product;

	static Product product1;

	static List<Product> productList;

	static List<Product> productsList;

	@BeforeAll
	public static void setUp() {
		productDto = new ProductDto();
		productDto.setProductName("realme7mist");
		productDto.setBrand("realme");
		productDto.setColor("Black");
		productDto.setDescription("Added with new features");
		productDto.setId(1L);
		productDto.setPrice(new BigDecimal(15999));
		productDto.setAvailability(1);

		product = new Product();
		product.setProductName("realme7mist");
		product.setBrand("realme");
		product.setColor("Black");
		product.setDescription("Added with new features");
		product.setId(1L);
		product.setPrice(new BigDecimal(15999));
		product.setAvailability(1);

		product1 = new Product();
		product1.setProductName("redmi 10 pro");
		product1.setBrand("xioami");
		product1.setColor("surphire blue");
		product1.setDescription("Cam quality is superb");
		product1.setId(2L);
		product1.setPrice(new BigDecimal(21999));
		product1.setAvailability(1);

		productList = new ArrayList<Product>();
		productList.add(product);

		productsList = new ArrayList<Product>();
		productsList.add(product);
		productsList.add(product1);

	}

	@Test
	@DisplayName("Create Product")
	void addProductTest() {

		// context
		when(productService.addProduct(productDto)).thenReturn(product);

		// event
		ResponseEntity<Product> result = productController.addProduct(productDto);
		verify(productService).addProduct(productDto);

		// outcome
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	@DisplayName("Get Product By Product Name")
	void getProductByNameTest() {

		// context
		when(productService.getAllProductByName("realme7mist")).thenReturn(productList);

		// event
		ResponseEntity<List<Product>> result = productController.getProductByName("realme7mist");
		verify(productService).getAllProductByName("realme7mist");

		// outcome
		assertEquals(productList, result.getBody());
	}

	@Test
	@DisplayName("Get Product By Id")
	void getProductByIdTest() {

		// context
		when(productService.retrieveProductById(2L)).thenReturn(product1);

		// event
		ResponseEntity<Product> result = productController.getProductById(2L);
		verify(productService).retrieveProductById(2L);

		// outcome
		assertEquals(product1, result.getBody());
	}

	@Test
	@DisplayName("Get All Products")
	void getAllProducts() {

		// context
		when(productService.getAllProducts()).thenReturn(productsList);

		// event
		ResponseEntity<List<Product>> result = productController.retrieveProducts();
		verify(productService).getAllProducts();

		// outcome
		assertEquals(productsList, result.getBody());
	}

	@Test
	@DisplayName("Get Products By Brand")
	void getProductByBrand() {

		// context
		when(productService.getProductByBrand("realme")).thenReturn(productList);

		// event
		ResponseEntity<List<Product>> result = productController.searchByBrand("realme");
		verify(productService).getProductByBrand("realme");

		// outcome
		assertEquals(productList, result.getBody());
	}

	@Test
	@DisplayName("Get Product By Color And Brand")
	void getProductByColorAndBrand() {

		// context
		when(productService.getProductByBrandAndColor("xioami", "surphire blue")).thenReturn(productsList);

		// event
		ResponseEntity<List<Product>> result = productController.searchByBrandAndColor("xioami", "surphire blue");
		verify(productService).getProductByBrandAndColor("xioami", "surphire blue");

		// outcome
		assertEquals(productsList, result.getBody());
	}
}
