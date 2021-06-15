package com.feign.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feign.shopping.dto.UserDto;
import com.feign.shopping.feignclient.UserClient;
import com.feign.shopping.repository.UserRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.repository.entity.User;
import com.feign.shopping.service.CartService;
import com.feign.shopping.service.OrderService;

@RestController("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserClient userClient;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/{userId}/{cartId}")
	public ResponseEntity<Order> createOrder(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {
		Item cart = cartService.getCart(cartId);
		List<Item> items = new ArrayList<>();
		items.add(cart);
		UserDto user = userClient.findByUserId(userId);
		User saveUser = new User();
		BeanUtils.copyProperties(user, saveUser);
		userRepository.save(saveUser);
		if (cart != null && user != null) {
			Order order = orderService.saveOrder(items, user);
			Order result = orderService.getOrderById(order.getId());
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
