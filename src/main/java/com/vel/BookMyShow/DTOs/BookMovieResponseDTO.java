package com.vel.BookMyShow.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookMovieResponseDTO {
    private double amount;
    private Long bookingId;
    private ResponseStatus responseStatus;
}
