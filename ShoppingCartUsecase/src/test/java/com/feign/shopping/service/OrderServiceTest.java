package com.feign.shopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import com.feign.shopping.dto.OrderDto;
import com.feign.shopping.dto.UserDto;
import com.feign.shopping.repository.OrderRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.repository.entity.Product;
import com.feign.shopping.repository.entity.User;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderService orderService;

	static OrderDto orderDto;

	static Order order;

	static Item item;
	
	static List<Item> items;

	static UserDto user;

	static Product product;

	@BeforeAll
	public static void setUp() {
		user = new UserDto();
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
	}

	@Test
	@DisplayName("Save Order")
	void saveOrderTest() {

		// context
		when(orderRepository.save(any(Order.class))).thenAnswer(i -> {
			Order result = i.getArgument(0);
			result.setId(1L);
			order = result;
			return order;
		});

		// event
		Order result = orderService.saveOrder(items, user);

		// outcome
		assertEquals(order, result);
	}

}
