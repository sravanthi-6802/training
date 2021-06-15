package com.register.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.user.repository.entity.UserRegister;

public interface RegistrationRepository extends JpaRepository<UserRegister, Long> {

	Optional<UserRegister> findByEmailAndPassword(String email, String password);

	Optional<UserRegister> findByEmailAndMobile(String email, String mobile);

}
