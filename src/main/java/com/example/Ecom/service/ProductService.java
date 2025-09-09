package com.example.Ecom.service;

import com.example.Ecom.dto.ProductDTO;
import com.example.Ecom.dto.ProductImageDTO;
import com.example.Ecom.dto.ProductReviewDTO;
import com.example.Ecom.dto.ProductReviewRepository;
import com.example.Ecom.entity.ProductEntity;
import com.example.Ecom.entity.ProductReview;
import com.example.Ecom.repository.ProductRepository;
import com.example.Ecom.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReviewRepository productReviewRepository;
    public Map<String, Object> getAllProducts(int pageNo, int pageSize){
       Pageable pageable = PageRequest.of(pageNo, pageSize);
       Page<ProductEntity> products= productRepository.findAll(pageable);

       List<ProductDTO> productDTOS = products.stream().map(this::convertToDTO).collect(Collectors.toList());

        Map<String, Object> response= new HashMap<String, Object>();
        response.put("products", productDTOS);
        response.put("Total Products", products.getTotalElements());

        return response;

    }

    public ProductDTO convertToDTO(ProductEntity product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setRating(product.getRating());
        productDTO.setCategory(product.getCategory());
        productDTO.setSeller(product.getSeller());
        productDTO.setStock(product.getStock());
        productDTO.setNumberOfReviews(product.getNumberOfReviews());

        // reviews
        List<ProductReviewDTO> reviewDtos = product.getReviews().stream().map(review ->{
            ProductReviewDTO reviewDTO = new ProductReviewDTO();
            reviewDTO.setProductId(review.getId());
            reviewDTO.setComment(review.getComment());
            reviewDTO.setRating(review.getRating());

            return reviewDTO;
        }).collect(Collectors.toList());

        //images
        List<ProductImageDTO> imageDtos= product.getImages().stream().map(img ->{
            ProductImageDTO dto = new ProductImageDTO(img.getPublicId());
            return dto;
        }).collect(Collectors.toList());


//        productDTO.setReviews(product.getReviews());
        productDTO.setReviews(reviewDtos);
        productDTO.setImages(imageDtos);
        return productDTO;
    }


    public ProductEntity getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("products not found with this id:"+ id));
    }

    public List<ProductEntity> searchProducts(String category, Double minPrice, Double maxPrice, String keyword, Double rating){
        Specification<ProductEntity> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceBetween(minPrice,maxPrice)
                        .and(ProductSpecification.hasNameOrDescriptionLike(keyword))
                        .and(ProductSpecification.ratingGreaterThan(rating)));
        return productRepository.findAll(spec);
    }

    public void addReview(ProductReviewDTO productReviewDTO) {
      ProductEntity product = productRepository.findById(productReviewDTO.getProductId()).orElseThrow(()-> new RuntimeException("Not any product found this id: "+productReviewDTO.getProductId()));

      ProductReview review = new ProductReview();
      review.setComment(productReviewDTO.getComment());
      review.setRating(productReviewDTO.getRating());
      review.setProduct(product); // automatically save the product id

        productReviewRepository.save(review);

    }
}
