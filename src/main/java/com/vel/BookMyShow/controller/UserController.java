package com.vel.BookMyShow.controller;


import com.vel.BookMyShow.DTOs.ResponseStatus;
import com.vel.BookMyShow.DTOs.SignUPResponseDTO;
import com.vel.BookMyShow.DTOs.SignUpRequestDTO;
import com.vel.BookMyShow.models.User;
import com.vel.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @Autowired
    private UserService userService;

    public SignUPResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        try {
            User user =userService.signUp(signUpRequestDTO);
            return SignUPResponseDTO.builder()
                    .responseStatus(ResponseStatus.SUCCESS)
                    .userId(String.valueOf(user.getId()))
                    .build();
        }
        catch (Exception e){
            return SignUPResponseDTO.builder()
                    .responseStatus(ResponseStatus.FAILURE)
                    .build();
        }
    }
}
