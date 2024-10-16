package com.group7.flight.mapper;

import com.group7.flight.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    int deleteOrderById(int id);
    Order getOrderById(int id);
    List<Order> getOrdersByUsernameAndStatus(String username, String status);
}
