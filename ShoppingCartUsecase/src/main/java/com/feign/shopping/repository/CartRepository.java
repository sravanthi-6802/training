package com.feign.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feign.shopping.repository.entity.Item;

public interface CartRepository extends JpaRepository<Item, Long> {

	void deleteByIdAndProductId(Long cartId, Long productId);

}
