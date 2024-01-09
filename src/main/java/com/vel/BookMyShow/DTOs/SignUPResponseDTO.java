package com.vel.BookMyShow.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUPResponseDTO {
    private ResponseStatus responseStatus;

    private String userId;
}
