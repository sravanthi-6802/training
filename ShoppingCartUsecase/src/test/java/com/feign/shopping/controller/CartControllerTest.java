package com.feign.shopping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.repository.entity.Product;
import com.feign.shopping.service.CartService;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

	@Mock
	CartService cartService;

	@InjectMocks
	CartController cartController;

	static Item item;

	static Product product;

	static List<Order> orders;

	static Order order;

	@BeforeAll
	public static void setUp() {

		product = new Product();
		product.setProductName("realme7mist");
		product.setBrand("realme");
		product.setColor("Black");
		product.setDescription("Added with new features");
		product.setId(1L);
		product.setPrice(new BigDecimal(15999));
		product.setAvailability(1);

		item = new Item();
		item.setId(1L);
		item.setProduct(product);
		item.setQuantity(2);
		item.setSubTotal(new BigDecimal(31998));
		//item.setOrders(null);

	}

	@Test
	@DisplayName("Add Item to Cart")
	void addItemtoCartTest() {

		// context
		when(cartService.addtoCart(1L, 2)).thenReturn(item);

		// event
		ResponseEntity<Item> result = cartController.addItemToCart(1L, 2);
		verify(cartService).addtoCart(1L, 2);

		// outcome
		assertEquals(item, result.getBody());

	}

	@Test
	@DisplayName("Get Cart")
	void getCartTest() {

		// context
		when(cartService.getCart(1L)).thenReturn(item);

		// event
		ResponseEntity<Item> result = cartController.getCart(1L);
		verify(cartService).getCart(1L);

		// outcome
		assertEquals(item, result.getBody());
	}

}
