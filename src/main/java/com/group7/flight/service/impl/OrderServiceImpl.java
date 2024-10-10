package com.group7.flight.service.impl;

import com.group7.flight.dto.OrderDTO;
import com.group7.flight.entity.*;
import com.group7.flight.mapper.FlightMapper;
import com.group7.flight.mapper.OrderMapper;
import com.group7.flight.mapper.SeatMapper;
import com.group7.flight.service.OrderService;
import com.group7.flight.vo.FlightVO;
import com.group7.flight.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    SeatMapper seatMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    FlightMapper flightMapper;

    /*
     * 首先根据seat_type和seat_number查询seat_id
     * 确认seat没有被卖掉后，将seat转为被卖掉
     * 然后创建一个Order对象
     **/
    @Override
    @Transactional
    public boolean createOrder(String username, OrderDTO orderDTO) {
        Seat seat = seatMapper.getSeatByFlightIdAndSeatNumberAndSeatType(orderDTO.getFlightId(),
                        orderDTO.getSeatType(), orderDTO.getSeatNumber());
        if (seat.isSold()) {
            return false;
        } else {
            seat.setSold(true);
            seatMapper.updateSeatSold(orderDTO.getFlightId(),
                    orderDTO.getSeatType(), orderDTO.getSeatNumber());
            Order order = new Order(orderDTO.getFlightId(), orderDTO.getSeatType(), orderDTO.getSeatNumber(),
                    orderDTO.getTotalPrice(), orderDTO.getPassengerName(), username, orderDTO.getEmail(), orderDTO.getPhone(),
                    orderDTO.isBaggageService(), orderDTO.isFoodService());
            if (orderMapper.insertOrder(order) == 1) {
                return true;
            }
            return false;
        }
    }

    /*
     * 根据order和status查询出对应的订单
     * 根据每个order的flight_id查询出航班相关信息，并创建flightVO对象
     * 然后创建OrderVO对象，加入list中
     **/
    @Override
    public List<OrderVO> getAllOrders(String username, String status) {
        List<OrderVO> res = new ArrayList<>();
        List<Order> orders = orderMapper.getOrdersByUsernameAndStatus(username, status);
        for (Order order : orders) {
            log.info(order.toString());
            Flight flight = flightMapper.getFlightById(order.getFlightId());
            log.info(flight.toString());
            Airplane airplane = flightMapper.getAirplaneById(flight.getAirplaneId());
            Airport deptAirport = flightMapper.getAirportByID(flight.getRoute().getDepartureAirportId());
            Airport arrAirport = flightMapper.getAirportByID(flight.getRoute().getArrivalAirportId());
            Airport stopoverAirport = new Airport();
            if (flight.getRoute().getStopoverAirportId() != 0) {
                stopoverAirport = flightMapper.getAirportByID(flight.getRoute().getStopoverAirportId());
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String deptTime = dateFormat.format(flight.getDepartureTime());
            String arrTime = dateFormat.format(flight.getArriveTime());

            FlightVO flightVO = new FlightVO(flight.getFlightNumber(), deptTime, arrTime, airplane.getModel(),
                    deptAirport.getAirportId(), deptAirport.getAirportCode(),
                    arrAirport.getAirportId(), arrAirport.getAirportCode(),
                    stopoverAirport.getAirportId(), stopoverAirport.getAirportCode(), flight.getRoute().getDurationHours());
            OrderVO orderVO = new OrderVO(order.getOrderId(), order.getPassengerName(), order.getSeatNumber(), order.getSeatType(),
                    order.getTotalPrice(), dateFormat.format(order.getOrderTime()), order.getStatus(), flightVO);
            res.add(orderVO);

        }
        return res;
    }
}
