package com.feign.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feign.shopping.dto.CustomTransaction;
import com.feign.shopping.dto.OrderDto;
import com.feign.shopping.dto.TransferFundRequest;
import com.feign.shopping.feignclient.FundTransferClient;
import com.feign.shopping.repository.OrderRepository;
import com.feign.shopping.repository.entity.Order;
import com.feign.shopping.service.OrderService;

@RestController
@Validated
public class PaymentController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FundTransferClient fundTransferClient;
	
	final static String toAccountNumber = "9876543456789";
	
	@PostMapping("/pay/{orderId}")
	public ResponseEntity<Void> pay(@Valid @RequestBody TransferFundRequest pay, @PathVariable("orderId") Long orderId) {
		Order order = orderService.getOrderById(orderId);
		pay.setToAccountNumber(toAccountNumber);
		pay.setAmount(order.getTotal().intValue());
		
		CustomTransaction transaction = fundTransferClient.transferFundRequest(pay);
		if(transaction != null) {
			order.setStatus("PAYMENT_COMPLETED");
			orderRepository.save(order);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.GATEWAY_TIMEOUT);
		}
	}

}
