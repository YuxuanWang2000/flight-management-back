package com.group7.flight.entity;

import lombok.Data;

@Data
public class Route {
    private int routeId;                     // flight id
    private int departureAirportId;
    private int arrivalAirportId;
    private int stopoverAirportId;
    private int distanceKm;                   // airline distance（km）
    private double durationHours;             // airline time（hour）
    private String routeType;                 // Route type (direct or stepover)

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
