package com.group7.flight.scheduler;

import com.group7.flight.entity.Flight;
import com.group7.flight.mapper.FlightMapper;
import com.group7.flight.mapper.SeatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class FlightScheduler {
    @Autowired
    FlightMapper flightMapper;

    @Autowired
    SeatMapper seatMapper;

    /*
     * Add scheduled flight tasks and add flight data for the next 14 days
    **/
    @Scheduled(cron = "0 0 0 * * ?")
    public void addFutureFlights() {
        // Get current date
        LocalDateTime flightDate = LocalDateTime.now();

        // generate flights for the next 14 days
        for (int i = 1; i <= 14; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = flightDate.format(formatter);
            log.info(formattedDate + ": " + flightMapper.selectFlightByDate(formattedDate));
            if (flightMapper.selectFlightByDate(formattedDate) == 0) {
                generateFlightForDay(flightDate);
            }
            flightDate = flightDate.plusDays(1);
        }
    }

    // Different dates generate different data, differentiated by day of the week
    private void generateFlightForDay(LocalDateTime flightDate) {
        DayOfWeek dayOfWeek = flightDate.getDayOfWeek();
        log.info(String.valueOf(dayOfWeek));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = flightDate.format(formatter);
        switch (dayOfWeek){
            case MONDAY:
                flightMapper.insertFlightMonday(formattedDate);
                break;
            case TUESDAY:
                flightMapper.insertFlightTuesday(formattedDate);
                break;
            case WEDNESDAY:
                flightMapper.insertFlightWednesday(formattedDate);
                break;
            case THURSDAY:
                flightMapper.insertFlightThursday(formattedDate);
                break;
            case FRIDAY:
                flightMapper.insertFlightFriday(formattedDate);
                break;
            case SATURDAY:
                flightMapper.insertFlightSaturday(formattedDate);
                break;
            case SUNDAY:
                flightMapper.insertFlightSunday(formattedDate);
                break;
        }
        List<Flight> flights = flightMapper.getFlightByDate(formattedDate);
        for (Flight flight :
                flights) {
            log.info(String.valueOf(flight.getFlightId()));
            if (seatMapper.getSeatByFlightId(flight.getFlightId()) == 0) {
                seatMapper.insertSeatByFlightId(flight.getFlightId());
            }
        }
    }

}
