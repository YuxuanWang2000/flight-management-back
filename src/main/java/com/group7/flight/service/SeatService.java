package com.group7.flight.service;

import com.group7.flight.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    List<Seat> getBusinessSeatByFlightId(int flightId);
    List<Seat> getEconomySeatByFlightId(int flightId);
}
