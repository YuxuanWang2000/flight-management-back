package com.group7.flight.mapper;

import com.group7.flight.entity.Seat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatMapper {
    List<Seat> getBusinessSeatByFlightId(int flightId);
    List<Seat> getEconomySeatByFlightId(int flightId);
    Seat getSeatByFlightIdAndSeatNumberAndSeatType(int flightId, String seatType, String seatNumber);
    int getSeatByFlightId(int flightId);
    int updateSeatSold(int flightId, String seatType, String seatNumber, boolean sold);
    int insertSeatByFlightId(int flightId);
}
