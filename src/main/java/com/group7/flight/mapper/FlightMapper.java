package com.group7.flight.mapper;

import com.group7.flight.entity.Airplane;
import com.group7.flight.entity.Airport;
import com.group7.flight.entity.Flight;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface FlightMapper {
    List<Airport> getAllAirports();
    List<Flight> getFlightsByDeptArrAndDate(int deptId, int arrId, Date date);
    Airport getAirportByID(int id);
    Airplane getAirplaneById(int id);
}
