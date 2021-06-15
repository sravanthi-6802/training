package com.feign.shopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.feign.shopping.repository.CartRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Product;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

	@Mock
	CartRepository cartRepository;

	@Mock
	ProductService productService;

	@InjectMocks
	CartService cartService;

	static Product product;

	static Item item;

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
	@DisplayName("Add item to Cart")
	void addItemtoCartTest() {

		// context
		when(cartRepository.save(any(Item.class))).thenAnswer(i -> {
			Item cart = i.getArgument(0);
			cart.setId(1L);
			item = cart;
			return item;
		});
		when(productService.retrieveProductById(1L)).thenReturn(product);

		// event
		Item result = cartService.addtoCart(1L, 2);

		// outcome
		assertEquals(item, result);
	}

	@Test
	@DisplayName("Get Cart")
	void getCartTest() {

		// context
		when(cartRepository.findById(1L)).thenReturn(Optional.of(item));

		// event
		Item result = cartService.getCart(1L);
		verify(cartRepository).findById(1L);

		// outcome
		assertEquals(item, result);
	}
}
