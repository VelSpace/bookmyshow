package com.vel.BookMyShow.service;

import com.vel.BookMyShow.DTOs.BookMovieRequestDTO;
import com.vel.BookMyShow.DTOs.BookMovieResponseDTO;
import com.vel.BookMyShow.models.*;
import com.vel.BookMyShow.repositories.BookingRepository;
import com.vel.BookMyShow.repositories.ShowRepository;
import com.vel.BookMyShow.repositories.ShowSeatRepository;
import com.vel.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(BookMovieRequestDTO bookMovieRequestDTO){
        Optional<User> OptionalUser = userRepository.findById(bookMovieRequestDTO.getUserId());
        if(OptionalUser.isEmpty()) throw new RuntimeException("User not found");
        User user = OptionalUser.get();


        Optional<Show> OptionalShow = showRepository.findById(bookMovieRequestDTO.getShowId());
        if(OptionalShow.isEmpty()) throw new RuntimeException("Show not available");

        Show show = OptionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(bookMovieRequestDTO.getShowSeatIds());

        for (ShowSeat showSeat : showSeats) {
            if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (Objects.equals(showSeat.getShowSeatStatus(), ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))) {
                throw new RuntimeException("show seats are not available");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setUser(user);
        booking.setShow(show);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats, show));

        return bookingRepository.save(booking);
    }
}
