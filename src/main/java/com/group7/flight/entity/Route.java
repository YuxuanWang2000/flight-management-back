package com.group7.flight.entity;

import lombok.Data;

@Data
public class Route {
    private int routeId;                     // 航线唯一ID
    private int departureAirportId;          // 出发机场ID
    private int arrivalAirportId;            // 到达机场ID
    private int stopoverAirportId;       // 中转机场ID（可以为null）
    private int distanceKm;                   // 航线距离（公里）
    private double durationHours;             // 航程时长（小时）
    private String routeType;                 // 航线类型（直飞或中转）

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", departureAirportId=" + departureAirportId +
                ", arrivalAirportId=" + arrivalAirportId +
                ", stopoverAirportId=" + stopoverAirportId +
                ", distanceKm=" + distanceKm +
                ", durationHours=" + durationHours +
                ", routeType='" + routeType + '\'' +
                '}';
    }
}
