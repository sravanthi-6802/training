package com.feign.shopping.dto;

import java.math.BigDecimal;
import java.util.List;

import com.feign.shopping.repository.entity.Order;

public class CartDto {

	private Long id;

	private int quantity;

	private BigDecimal subTotal;

	private Long productId;

	private List<Order> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public CartDto() {
		super();
	}

	public CartDto(int quantity, BigDecimal subTotal, Long productId) {
		super();
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.productId = productId;
	}

}
