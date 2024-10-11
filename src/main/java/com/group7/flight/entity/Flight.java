package com.group7.flight.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Flight {
    int flightId;
    String flightNumber;
    int airplaneId;
    Route route;
    Date departureTime;
    Date arriveTime;
    BigDecimal price;
    String status;

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", airplaneId=" + airplaneId +
                ", route=" + route +
                ", departureTime=" + departureTime +
                ", arriveTime=" + arriveTime +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
