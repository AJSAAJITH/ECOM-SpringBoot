package com.example.Ecom.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private Double totalItmAmount;
    private Double taxAmount;
    private Double totalAmount;
    private String status;
    private String referenceId;

    public Order(){}

    public Order(List<OrderItem> orderItems, Double totalItmAmount, Double taxAmount, Double totalAmount, String status, String referenceId) {
        this.orderItems = orderItems;
        this.totalItmAmount = totalItmAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.status = status;
        this.referenceId = referenceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalItmAmount() {
        return totalItmAmount;
    }

    public void setTotalItmAmount(Double totalItmAmount) {
        this.totalItmAmount = totalItmAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String orderNo) {
        this.referenceId = orderNo;
    }
}
