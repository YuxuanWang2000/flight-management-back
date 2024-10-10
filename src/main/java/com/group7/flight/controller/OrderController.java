package com.group7.flight.controller;


import com.group7.flight.dto.OrderDTO;
import com.group7.flight.response.ResponseResult;
import com.group7.flight.service.OrderService;
import com.group7.flight.util.JwtUtil;
import com.group7.flight.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseResult<Boolean> createOrder(HttpServletRequest request, @RequestBody OrderDTO orderDTO) {
        log.info(orderDTO.toString());
        String username = JwtUtil.getUsernameFromToken(request.getHeader("token"));
        return ResponseResult.success(orderService.createOrder(username, orderDTO));
    }

    @GetMapping("/allOrders")
    public ResponseResult<List<OrderVO>> getAllOrders(HttpServletRequest request){
        String username = JwtUtil.getUsernameFromToken(request.getHeader("token"));
        log.info("getAllOrder: " + username);
        return ResponseResult.success(orderService.getAllOrders(username, ""));
    }

    @GetMapping("/completedOrders")
    public ResponseResult<List<OrderVO>> getCompletedOrders(HttpServletRequest request){
        String username = JwtUtil.getUsernameFromToken(request.getHeader("token"));
        log.info("getAllOrder: " + username);
        return ResponseResult.success(orderService.getAllOrders(username, "completed"));
    }

    @GetMapping("/readyOrders")
    public ResponseResult<List<OrderVO>> getUnCompletedOrders(HttpServletRequest request){
        String username = JwtUtil.getUsernameFromToken(request.getHeader("token"));
        log.info("getAllOrder: " + username);
        return ResponseResult.success(orderService.getAllOrders(username, "ready"));
    }
}
