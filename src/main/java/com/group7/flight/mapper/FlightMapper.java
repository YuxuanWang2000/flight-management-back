package com.group7.flight.mapper;

import com.group7.flight.dto.FlightDTO;
import com.group7.flight.entity.Airplane;
import com.group7.flight.entity.Airport;
import com.group7.flight.entity.Flight;
import com.group7.flight.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.awt.peer.LightweightPeer;
import java.util.Date;
import java.util.List;

@Mapper
public interface FlightMapper {
    List<Airport> getAllAirports();
    List<Flight> getFlightsByDeptArrAndDate(int deptId, int arrId, Date date);
    Airport getAirportByID(int id);
    Airplane getAirplaneById(int id);
    Flight getFlightById(int id);
    List<Flight> getFlightByDate(String date);
    int selectFlightByDate(String date);
    int insertFlightMonday(String date);
    int insertFlightTuesday(String date);
    int insertFlightWednesday(String date);
    int insertFlightThursday(String date);
    int insertFlightFriday(String date);
    int insertFlightSaturday(String date);
    int insertFlightSunday(String date);
}
