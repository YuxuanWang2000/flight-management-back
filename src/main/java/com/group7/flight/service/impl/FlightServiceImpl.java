package com.group7.flight.service.impl;

import com.group7.flight.dto.FlightDTO;
import com.group7.flight.entity.Airplane;
import com.group7.flight.entity.Airport;
import com.group7.flight.entity.Flight;
import com.group7.flight.entity.Route;
import com.group7.flight.mapper.FlightMapper;
import com.group7.flight.service.FlightService;
import com.group7.flight.vo.AirportVO;
import com.group7.flight.vo.FlightVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightMapper flightMapper;

    @Override
    public List<AirportVO> getAllAirports() {
        List<Airport> allAirports = flightMapper.getAllAirports();
        List<AirportVO> airportVOS = new ArrayList<>();
        for (Airport airport :
                allAirports) {
            airportVOS.add(new AirportVO(airport.getAirportId(),
                    airport.getAirportName() + "(" + airport.getAirportCode() + ")"));
        }

        return airportVOS;
    }

    @Override
    public List<FlightVO> getFlights(FlightDTO flightDTO) {
        log.info("flight id: " + flightDTO.getDeptId() + " " + flightDTO.getArrId());
        log.info("date: " + flightDTO.getDate());
        List<Flight> flightsByDeptArrAndDate = flightMapper.getFlightsByDeptArrAndDate(flightDTO.getDeptId(), flightDTO.getArrId(), flightDTO.getDate());
        log.info(String.valueOf(flightsByDeptArrAndDate.size()));

        ArrayList<FlightVO> res = new ArrayList<>();
        for (Flight flight : flightsByDeptArrAndDate) {
            log.info(flight.getFlightNumber());
            log.info(flight.toString());
            Route route = flight.getRoute();
            Airport deptAirport = flightMapper.getAirportByID(route.getDepartureAirportId());
            Airport arrAirport = flightMapper.getAirportByID(route.getArrivalAirportId());
            Airport stopoverAirport = new Airport();
            if (route.getStopoverAirportId() != 0) {
                stopoverAirport = flightMapper.getAirportByID(route.getStopoverAirportId());
            }
            Airplane airplane = flightMapper.getAirplaneById(flight.getAirplaneId());

            double consuming = route.getDurationHours();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            res.add(new FlightVO(flight.getFlightId(), flight.getFlightNumber(),
                    dateFormat.format(flight.getDepartureTime()), dateFormat.format(flight.getArriveTime()),
                    flight.getAirplaneId(), airplane.getModel(), deptAirport.getAirportId(), deptAirport.getAirportCode(),
                    arrAirport.getAirportId(), arrAirport.getAirportCode(),
                    stopoverAirport.getAirportId(), stopoverAirport.getAirportCode(),
                    consuming, flight.getPrice(), flight.getStatus()));
        }

        log.info(res.toString());
        return res;
    }
}
