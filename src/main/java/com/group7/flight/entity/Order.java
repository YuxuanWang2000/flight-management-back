package com.group7.flight.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Order {
    private int orderId;             // Unique order ID
    private int flightId;            // Reference to flight ID
    private String seatType;
    private String seatNumber;
    private double totalPrice;   // Total price for the booking
    private String passengerName;    // Passenger's name
    private String username;
    private String email;            // Passenger's email
    private String phone;            // Passenger's phone
    private boolean baggageService;  // Baggage service request
    private boolean foodService;     // Food service request
    private Date orderTime; // Order timestamp
    private String status;           // Order status (e.g., 'ready', 'completed')

    public Order() {
    }

    public Order(int flightId, String seatType, String seatNumber, double totalPrice, String passengerName, String username, String email, String phone, boolean baggageService, boolean foodService) {
        this.flightId = flightId;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.username = username;
        this.totalPrice = totalPrice;
        this.passengerName = passengerName;
        this.email = email;
        this.phone = phone;
        this.baggageService = baggageService;
        this.foodService = foodService;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", flightId=" + flightId +
                ", seatType='" + seatType + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", passengerName='" + passengerName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", baggageService=" + baggageService +
                ", foodService=" + foodService +
                ", orderTime=" + orderTime +
                ", status='" + status + '\'' +
                '}';
    }
}
