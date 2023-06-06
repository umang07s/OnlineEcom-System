package com.onlineECSystem.controller;

import com.onlineECSystem.Dto.OrderDTO;
import com.onlineECSystem.entity.Order;
import com.onlineECSystem.entity.OrderProduct;
import com.onlineECSystem.repository.OrderProductRepository;
import com.onlineECSystem.repository.OrderRepository;
import com.onlineECSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> editOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order editedOrder = orderService.editOrder(orderId, orderDTO);
        return ResponseEntity.ok(editedOrder);
    }


}
