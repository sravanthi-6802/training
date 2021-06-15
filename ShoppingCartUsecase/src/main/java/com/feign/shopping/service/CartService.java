package com.feign.shopping.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feign.shopping.repository.CartRepository;
import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.repository.entity.Product;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	public Item addtoCart(Long productId, Integer quantity) {
		Product product = productService.retrieveProductById(productId);
		Item item = new Item(quantity, getSubTotalForItem(product, quantity), product);
		return cartRepository.save(item);
	}

	public Item getCart(Long cartId) {
		return cartRepository.findById(cartId).get();
	}

	public static BigDecimal getSubTotalForItem(Product product, int quantity) {
		return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
	}

	public void deleteProductFromCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}

}
