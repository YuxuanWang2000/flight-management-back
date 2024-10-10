package com.group7.flight.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    int flightId;
    String seatType;
    String seatNumber;
    private double totalPrice;
    private String passengerName;
    private String email;
    private String phone;
    private boolean baggageService;
    private boolean foodService;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "flightId=" + flightId +
                ", seatType='" + seatType + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", passengerName='" + passengerName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", baggageService=" + baggageService +
                ", foodService=" + foodService +
                '}';
    }
}
