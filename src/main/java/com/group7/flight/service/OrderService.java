package com.group7.flight.service;

import com.group7.flight.dto.OrderDTO;
import com.group7.flight.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public boolean createOrder(String username, OrderDTO orderDTO);
    public List<OrderVO> getAllOrders(String name, String status);
}
