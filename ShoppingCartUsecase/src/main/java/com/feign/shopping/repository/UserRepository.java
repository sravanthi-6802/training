package com.feign.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feign.shopping.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
