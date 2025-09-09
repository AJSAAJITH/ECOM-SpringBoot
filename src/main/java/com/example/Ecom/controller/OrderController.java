package com.example.Ecom.controller;

import com.example.Ecom.dto.CreateOrderRequest;
import com.example.Ecom.dto.OrderCreatedDTO;
import com.example.Ecom.entity.Order;
import com.example.Ecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest){
       OrderCreatedDTO order = orderService.createOrder(orderRequest);
       return ResponseEntity.ok().body(order);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrder(@PathVariable("referenceId") String referenceId){
       Order order = orderService.getOrder(referenceId);
       if(order == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
       }
       return ResponseEntity.ok().body(order);
    }
}
