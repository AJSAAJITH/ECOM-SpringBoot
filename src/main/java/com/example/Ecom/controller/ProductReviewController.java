package com.example.Ecom.controller;

import com.example.Ecom.dto.ProductReviewDTO;
import com.example.Ecom.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/reviews")
public class ProductReviewController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<?> addReview(@RequestBody @Valid ProductReviewDTO productReviewDTO){
        productService.addReview(productReviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added");
    }
}
