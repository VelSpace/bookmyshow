package com.vel.BookMyShow.service;

import com.vel.BookMyShow.DTOs.SignUpRequestDTO;
import com.vel.BookMyShow.models.User;
import com.vel.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(SignUpRequestDTO signUpRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already existing");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(signUpRequestDTO.getEmail())
                .password(encoder.encode(signUpRequestDTO.getPassword()))
                .build();
        return userRepository.save(user);
    }
}




