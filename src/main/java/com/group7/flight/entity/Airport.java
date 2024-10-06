package com.group7.flight.entity;

import lombok.Data;

@Data
public class Airport {
    private int airportId;       // 机场唯一ID
    private String airportCode;  // 机场代码（IATA或ICAO代码）
    private String airportName;  // 机场名称
    private String city;         // 机场所在城市
    private String region;       // 机场所在国家或区域
    private String timezone;     // 机场所在时区
}
