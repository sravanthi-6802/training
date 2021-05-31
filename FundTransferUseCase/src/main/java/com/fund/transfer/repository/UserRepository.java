package com.fund.transfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.transfer.repository.entity.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

	Optional<CustomUser> findByMailIdAndPassword(String mail, String password);

	Optional<CustomUser> findByMailId(String mail);

}
