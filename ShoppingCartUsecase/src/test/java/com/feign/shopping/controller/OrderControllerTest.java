package com.feign.shopping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.feign.shopping.dto.OrderDto;
import com.feign.shopping.dto.UserDto;
import com.feign.shopping.feignclient.UserClient;
import com.feign.shopping.repository.UserRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.repository.entity.Product;
import com.feign.shopping.repository.entity.User;
import com.feign.shopping.service.CartService;
import com.feign.shopping.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@Mock
	OrderService orderService;

	@Mock
	CartService cartService;

	@Mock
	UserClient userClient;
	
	@Mock
	UserRepository userRepository;

	@InjectMocks
	OrderController orderController;

	static OrderDto orderDto;

	static Order order;

	static User user;

	static UserDto userDto;

	static Product product;

	static Item item;

	static List<Item> items;

	@BeforeAll
	public static void setUp() {
		user = new User();
		user.setId(1L);
		user.setFirstName("sravanthi");
		//user.setOrders(null);

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

		items = new ArrayList<Item>();
		items.add(item);

		orderDto = new OrderDto();
		orderDto.setId(1L);
		orderDto.setUserId(1L);
		orderDto.setOrderedDate(LocalDate.now());
		orderDto.setStatus("PAYMENT_EXPECTED");
		orderDto.setTotal(new BigDecimal(15999));
		orderDto.setItems(items);

		order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setOrderedDate(LocalDate.now());
		order.setStatus("PAYMENT_EXPECTED");
		order.setTotal(new BigDecimal(15999));
		order.setItems(items);

		userDto = new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("sravanthi");
	}

	@Test
	@DisplayName("Create Order")
	void addOrderTest() {

		// context
		when(orderService.saveOrder(items, userDto)).thenReturn(order);
		when(orderService.getOrderById(1L)).thenReturn(order);
		when(cartService.getCart(1L)).thenReturn(item);
		when(userClient.findByUserId(1L)).thenReturn(userDto);
		when(userRepository.save(any(User.class))).thenAnswer(i->{
			User users = i.getArgument(0);
			users.setId(1L);
			user = users;
			return user;
		});

		// event
		ResponseEntity<Order> result = orderController.createOrder(1L, 1L);

		// outcome
		assertEquals(order, result.getBody());
	}

}
