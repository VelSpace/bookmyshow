package com.vel.BookMyShow.service;

import com.vel.BookMyShow.models.Show;
import com.vel.BookMyShow.models.ShowSeat;
import com.vel.BookMyShow.models.ShowSeatType;
import com.vel.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;

    public double calculatePrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        double amount = 0.0;

        for(ShowSeat showSeat : showSeats){
            for (ShowSeatType seatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(seatType.getSeatType())){
                    amount += seatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
