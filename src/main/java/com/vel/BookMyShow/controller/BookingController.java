package com.vel.BookMyShow.controller;

import com.vel.BookMyShow.DTOs.BookMovieRequestDTO;
import com.vel.BookMyShow.DTOs.BookMovieResponseDTO;
import com.vel.BookMyShow.DTOs.ResponseStatus;
import com.vel.BookMyShow.models.Booking;
import com.vel.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {
        try {
            Booking booking = bookingService.bookMovie(bookMovieRequestDTO);
            return BookMovieResponseDTO.builder()
                    .bookingId(booking.getId())
                    .responseStatus(ResponseStatus.SUCCESS)
                    .amount(booking.getAmount())
                    .build();

        } catch (Exception e) {
            return BookMovieResponseDTO.builder()
                    .responseStatus(ResponseStatus.FAILURE)
                    .build();
        }
    }
}
