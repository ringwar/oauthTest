package com.dev.vuetest;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dev.vuetest.config.CustomUserDetails;
import com.dev.vuetest.entity.Role;
import com.dev.vuetest.entity.User;
import com.dev.vuetest.repository.UserRepository;
import com.dev.vuetest.service.UserService;

@SpringBootApplication
public class Vuetest1Application {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Vuetest1Application.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder,
			UserRepository repository, UserService userService ) throws Exception {
		if (repository.count()==0)
			userService.save(new User("admin", "admin", Arrays.asList(new Role("USER"), new Role("ACTUATOR"), new Role("ADMIN"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	private UserDetailsService  userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
}