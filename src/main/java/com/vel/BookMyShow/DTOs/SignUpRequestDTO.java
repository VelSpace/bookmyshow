package com.vel.BookMyShow.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequestDTO {
    private String email;

    private String password;

}
