package com.group7.flight.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class FlightVO {
    int id;
    String flightCode;
    String deptTime;
    String arrTime;
    int planeId;
    String planeModel;
    int deptAirportId;
    String deptAirportCode;
    int arrAirportId;
    String arrAirportCode;
    int stopoverAirportId;
    String stopoverAirportCode;
    double consuming;
    BigDecimal price;
    String status;

    public FlightVO(String flightCode, String deptTime, String arrTime, String planeModel,
                    int deptAirportId, String deptAirportCode, int arrAirportId, String arrAirportCode,
                    int stopoverAirportId, String stopoverAirportCode, double consuming) {
        this.flightCode = flightCode;
        this.deptTime = deptTime;
        this.arrTime = arrTime;
        this.planeModel = planeModel;
        this.deptAirportId = deptAirportId;
        this.deptAirportCode = deptAirportCode;
        this.arrAirportId = arrAirportId;
        this.arrAirportCode = arrAirportCode;
        this.stopoverAirportId = stopoverAirportId;
        this.stopoverAirportCode = stopoverAirportCode;
        this.consuming = consuming;
    }

    @Override
    public String toString() {
        return "FlightVO{" +
                "id=" + id +
                ", flightCode='" + flightCode + '\'' +
                ", deptTime=" + deptTime +
                ", arrTime=" + arrTime +
                ", planeId=" + planeId +
                ", planeModel='" + planeModel + '\'' +
                ", deptAirportId=" + deptAirportId +
                ", deptAirportCode='" + deptAirportCode + '\'' +
                ", arrAirportId=" + arrAirportId +
                ", arrAirportCode='" + arrAirportCode + '\'' +
                ", stopoverAirportId=" + stopoverAirportId +
                ", stopoverAirportCode='" + stopoverAirportCode + '\'' +
                ", consuming=" + consuming +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
