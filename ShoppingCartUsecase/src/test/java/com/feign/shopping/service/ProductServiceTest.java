package com.feign.shopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.feign.shopping.dto.ProductDto;
import com.feign.shopping.repository.ProductRepository;
import com.feign.shopping.repository.entity.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductService productService;

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
	@DisplayName("Save Product")
	void addProductTest() {

		// context
		when(productRepository.save(any(Product.class))).thenAnswer(i -> {
			Product prod = i.getArgument(0);
			prod.setId(1L);
			product = prod;
			return product;
		});

		// event
		Product result = productService.addProduct(productDto);

		// outcome
		assertEquals(product, result);
	}

	@Test
	@DisplayName("Get Product By Id")
	void getProductByIdTest() {

		// context
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		// event
		Product result = productService.retrieveProductById(1L);
		verify(productRepository).findById(1L);

		// outcome
		assertEquals(product, result);
	}

	@Test
	@DisplayName("Get Products By Name")
	void getProductByNameTest() {

		// context
		when(productRepository.findAllByProductName("realme7mist")).thenReturn(productList);

		// event
		List<Product> result = productService.getAllProductByName("realme7mist");
		verify(productRepository).findAllByProductName("realme7mist");

		// outcome
		assertEquals(productList, result);
	}

	@Test
	@DisplayName("Get All Products")
	void getAllProductsTest() {

		// context
		when(productRepository.findAll()).thenReturn(productsList);

		// event
		List<Product> result = productService.getAllProducts();
		verify(productRepository).findAll();

		// outcome
		assertEquals(productsList, result);
	}
}
