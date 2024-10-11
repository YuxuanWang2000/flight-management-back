package com.group7.flight.vo;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderVO {
    int id;
    String passengerName;
    String seatNumber;
    String seatType;
    double price;
    String createdTime;
    String status;
    FlightVO flightVO;
}
