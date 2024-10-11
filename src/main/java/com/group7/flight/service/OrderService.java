package com.group7.flight.service;

import com.group7.flight.dto.OrderDTO;
import com.group7.flight.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    boolean createOrder(String username, OrderDTO orderDTO);
    List<OrderVO> getAllOrders(String name, String status);
    boolean deleteOrder(int id);
}
