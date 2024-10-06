package com.group7.flight.controller;


import com.group7.flight.dto.FlightDTO;
import com.group7.flight.response.ResponseResult;
import com.group7.flight.service.FlightService;
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

    // GET 请求，查询所有机场
    @GetMapping("/airports")
    public ResponseResult<List<AirportVO>> getAllAirports() {
        return ResponseResult.success(flightService.getAllAirports());
    }

    @PostMapping("/searchFlights")
    public ResponseResult<List<FlightVO>> searchFlights(@RequestBody FlightDTO flightDTO) {
        log.info("flight controller: " + flightDTO.toString());
        return ResponseResult.success(flightService.getFlights(flightDTO));
    }

}
