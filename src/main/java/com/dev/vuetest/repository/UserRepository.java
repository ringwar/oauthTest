package com.dev.vuetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.vuetest.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
