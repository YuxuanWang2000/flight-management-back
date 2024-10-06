package com.group7.flight.service;

import com.group7.flight.dto.FlightDTO;
import com.group7.flight.vo.AirportVO;
import com.group7.flight.vo.FlightVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    List<AirportVO> getAllAirports();
    List<FlightVO> getFlights(FlightDTO flightDTO);
}
