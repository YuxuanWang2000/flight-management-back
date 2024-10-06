package com.group7.flight.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class FlightVO {
    int id;
    String flightCode;
    Date deptTime;
    int planeId;
    String planeModel;
    int deptAirportId;
    String deptAirportCode;
    int arrAirportId;
    String arrAirportCode;
    int stopoverAirportId;
    String stopoverAirportCode;
    double consuming;
    BigDecimal price;
    String status;
}
