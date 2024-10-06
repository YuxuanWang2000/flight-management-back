package com.group7.flight.entity;

import lombok.Data;

@Data
public class Airplane {
    private Integer airplaneId;             // 飞机唯一ID
    private String model;                   // 飞机型号
    private Integer capacity;               // 乘客容量
    private String manufacturer;            // 制造商
    private Integer rangeKm;                // 飞机航程
    private String registrationNumber;      // 飞机注册号
}
