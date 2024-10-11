package com.group7.flight.service.impl;

import com.group7.flight.entity.Seat;
import com.group7.flight.mapper.SeatMapper;
import com.group7.flight.mapper.UserMapper;
import com.group7.flight.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatMapper seatMapper;

    @Override
    public List<Seat> getBusinessSeatByFlightId(int flightId) {
        return seatMapper.getBusinessSeatByFlightId(flightId);
    }

    @Override
    public List<Seat> getEconomySeatByFlightId(int flightId) {
        return seatMapper.getEconomySeatByFlightId(flightId);
    }
}
