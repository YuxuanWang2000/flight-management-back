package com.group7.flight.entity;

import lombok.Data;

@Data
public class Airplane {
    private Integer airplaneId;             // airplane ID
    private String model;                   // plane model
    private Integer capacity;               // passenger capacity
    private String manufacturer;            // manufacturer
    private Integer rangeKm;                // airplane range
    private String registrationNumber;      // airplane registration number
}
