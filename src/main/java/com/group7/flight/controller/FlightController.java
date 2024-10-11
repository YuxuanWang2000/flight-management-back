package com.group7.flight.controller;

import com.group7.flight.dto.FlightDTO;
import com.group7.flight.entity.Airport;
import com.group7.flight.entity.Seat;
import com.group7.flight.response.ResponseResult;
import com.group7.flight.service.FlightService;
import com.group7.flight.service.SeatService;
import com.group7.flight.vo.AirportVO;
import com.group7.flight.vo.FlightVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @Autowired
    SeatService seatService;

    // GET request, check all airports
    @GetMapping("/airports")
    public ResponseResult<List<AirportVO>> getAllAirports() {
        return ResponseResult.success(flightService.getAllAirports());
    }

    // search flights
    @PostMapping("/searchFlights")
    public ResponseResult<List<FlightVO>> searchFlights(@RequestBody FlightDTO flightDTO) {
        log.info("flight controller: " + flightDTO.toString());
        List<FlightVO> flights = flightService.getFlights(flightDTO);
        log.info(flights.toString());
        return ResponseResult.success(flights);
    }

    // get business seats
    @GetMapping("/businessSeat")
    public ResponseResult<List<Seat>> getFlightBusinessSeats(@RequestParam("flightId") Integer flightId) {
//        log.info(String.valueOf(flightId));
        return ResponseResult.success(seatService.getBusinessSeatByFlightId(flightId));
    }

    // get economy seats
    @GetMapping("/economySeat")
    public ResponseResult<List<Seat>> getFlightEconomySeats(@RequestParam("flightId") Integer flightId) {
//        log.info(String.valueOf(flightId));
        return ResponseResult.success(seatService.getEconomySeatByFlightId(flightId));
    }
}
