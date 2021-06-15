package com.feign.shopping.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feign.shopping.dto.OrderDto;
import com.feign.shopping.dto.UserDto;
import com.feign.shopping.repository.OrderRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.repository.entity.User;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order saveOrder(List<Item> cart, UserDto userDto) {
		OrderDto orderDto = createOrder(cart, userDto);
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		return orderRepository.save(order);
	}

	public static BigDecimal countTotalPrice(List<Item> cart) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < cart.size(); i++) {
			total = total.add(cart.get(i).getSubTotal());
		}
		return total;
	}

	public OrderDto createOrder(List<Item> cart, UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		OrderDto order = new OrderDto();
		order.setItems(cart);
		order.setUserId(userDto.getId());
		order.setTotal(countTotalPrice(cart));
		order.setOrderedDate(LocalDate.now());
		order.setStatus("PAYMENT_EXPECTED");
		return order;
	}

	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

}
