
package com.example.Ecom.dto;
import com.example.Ecom.entity.ProductImage;


import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Double rating = 0.0;
    private String seller;
    private Integer stock;
    private Integer numberOfReviews = 0;

    private List<ProductImageDTO> images;
    private List<ProductReviewDTO> reviews;

    public List<ProductImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDTO> images) {
        this.images = images;
    }

    public List<ProductReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public ProductDTO(){}

    public ProductDTO(String name, Double price, String description, String category, Double rating, String seller, Integer stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.rating = rating;
        this.seller = seller;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Integer numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
}
