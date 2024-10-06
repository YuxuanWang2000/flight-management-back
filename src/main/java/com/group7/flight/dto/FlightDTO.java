package com.group7.flight.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDTO {
    int deptId;
    int arrId;
    Date date;

    @Override
    public String toString() {
        return "FlightDTO{" +
                "deptId=" + deptId +
                ", arrId=" + arrId +
                ", date=" + date +
                '}';
    }
}
