package com.example.Ecom.dto;

public class OrderCreatedDTO {

    public OrderCreatedDTO(){}

    public OrderCreatedDTO(String referenceId) {
        this.referenceId = referenceId;
    }

    private String referenceId;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
