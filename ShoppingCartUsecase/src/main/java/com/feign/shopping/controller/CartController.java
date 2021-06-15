package com.feign.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.shopping.repository.entity.Item;
import com.feign.shopping.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping(params = { "productId", "quantity" })
	public ResponseEntity<Item> addItemToCart(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity) {
		return new ResponseEntity<>(cartService.addtoCart(productId, quantity), HttpStatus.CREATED);
	}

	@GetMapping("/{cartId}")
	public ResponseEntity<Item> getCart(@PathVariable("cartId") Long cartId) {
		return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
	}

	@DeleteMapping("/{productId}/{cartId}")
	public ResponseEntity<Void> removeProductFromCart(@PathVariable("productId") Long productId,
			@PathVariable("cartId") Long cartId) {
		Item item = cartService.getCart(cartId);
		if (item != null) {
			cartService.deleteProductFromCart(cartId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
