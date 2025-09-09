package com.example.Ecom.specifications;

import com.example.Ecom.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {

    public static Specification<ProductEntity> hasCategory(String category){
        return (root, query, criteriaBuilder)->
                category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<ProductEntity> priceBetween(Double min, Double max){
        return (root, query, criteriaBuilder) ->{
            if(min == null && max == null) return null;
            if(min == null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
            if (max == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);
            return criteriaBuilder.between(root.get("price"), min, max);
        };
    }

    public static Specification<ProductEntity> hasNameOrDescriptionLike(String keyword){
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()) return null;
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + keyword.toLowerCase() + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + keyword.toLowerCase() + "%")
            );
        };
    }

    public static Specification<ProductEntity> ratingGreaterThan(Double rating){
        return (root, query, criteriaBuilder)->{
            if (rating == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
        };
    }
}
