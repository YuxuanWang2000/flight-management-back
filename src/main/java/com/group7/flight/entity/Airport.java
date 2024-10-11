package com.group7.flight.entity;

import lombok.Data;

@Data
public class Airport {
    private int airportId;       // airport id
    private String airportCode;  // airport code
    private String airportName;  // airport name
    private String city;         // airport city
    private String region;       // airport country or range
    private String timezone;     // airport timeZone
}
