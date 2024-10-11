package com.group7.flight.service.impl;

import com.group7.flight.scheduler.FlightScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class ScheduledServiceImpl {
    @Autowired
    FlightScheduler flightScheduler;

    @PostConstruct
    public void init() {
        // When the project starts, execute a scheduled task immediately to generate data
        flightScheduler.addFutureFlights();
    }
}
