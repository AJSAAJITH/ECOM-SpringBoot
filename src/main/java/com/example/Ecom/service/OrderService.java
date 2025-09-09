package com.example.Ecom.service;

import com.example.Ecom.dto.CreateOrderRequest;
import com.example.Ecom.dto.OrderCreatedDTO;
import com.example.Ecom.dto.OrderItemDTO;
import com.example.Ecom.entity.Order;
import com.example.Ecom.entity.OrderItem;
import com.example.Ecom.entity.ProductEntity;
import com.example.Ecom.repository.OrderRepository;
import com.example.Ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private OrderRepository orderRepo;

    public OrderCreatedDTO createOrder(CreateOrderRequest orderRequest) {
        Order order = new Order();
        order.setStatus("PENDING");
        double totalItemsAmount = 0;


        for(OrderItemDTO item: orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());

            ProductEntity product = prodRepo.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            totalItemsAmount += item.getPrice() * item.getQuantity();

            order.getOrderItems().add(orderItem);

        }

        order.setTotalItmAmount(totalItemsAmount);
        double totalAmount = 0;
        double taxAmount = 10;
        totalAmount = totalItemsAmount  + taxAmount;
        order.setTotalAmount(totalAmount);
        order.setTaxAmount(taxAmount);
        String refId = UUID.randomUUID().toString();
        order.setReferenceId(refId);
        orderRepo.save(order);
        return new OrderCreatedDTO(refId);


    }

    public Order getOrder(String referenceId) {
        // TODO Auto-generated method stub
        return orderRepo.findByReferenceId(referenceId).orElseThrow(() -> new RuntimeException("No order found with Ref Id"));
    }
}
