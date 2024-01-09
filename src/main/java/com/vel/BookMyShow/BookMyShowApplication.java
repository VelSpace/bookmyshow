package com.vel.BookMyShow;

import com.vel.BookMyShow.DTOs.SignUpRequestDTO;
import com.vel.BookMyShow.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired(required = false)
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDTO signUpRequestDTO = SignUpRequestDTO.builder()
				.email("velspace01@gmail.com")
				.password("Hey@Universe@01")
				.build();

		userController.signUp(signUpRequestDTO);
	}
}
