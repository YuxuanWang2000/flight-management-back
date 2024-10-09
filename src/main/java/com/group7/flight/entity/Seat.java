package com.group7.flight.entity;


import lombok.Data;

@Data
public class Seat {
    private int id;
    private int FlightId;
    private String seatNumber;
    private String seatType;
    private boolean isSold;
}
