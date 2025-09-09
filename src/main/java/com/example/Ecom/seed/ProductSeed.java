package com.example.Ecom.seed;

import com.example.Ecom.entity.ProductEntity;
import com.example.Ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeed implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() ==  0){
            List<ProductEntity> demoProducts = List.of(
                    new ProductEntity("Iphone 15",24000.0,"Storage 256gb",4.5, "Phone","Amezon", 10, List.of("/products/1.jpg")),
                    new ProductEntity("Dell i7",150000.0,"Storage 256gb", 4.2,"Laptop","Amezon", 20 , List.of("/products/2.jpg")),
                    new ProductEntity("Iphone 14",120000.0,"Storage 256gb", 4.0,"Phone","AliExpress", 9 , List.of("/products/3.jpg")),
                    new ProductEntity("Y9 headset",14000.0,"Storage 128gb", 3.0,"Audio","Amezon", 7 , List.of("/products/4.jpg")),
                    new ProductEntity("Iphone 14",24000.0,"Storage 256gb",4.5, "Phone","Amezon", 10 , List.of("/products/5.jpg")),
                    new ProductEntity("Dell i9",150000.0,"Storage 256gb", 4.2,"Laptop","Amezon", 20 , List.of("/products/6.jpg")),
                    new ProductEntity("Iphone 12",120000.0,"Storage 256gb", 4.0,"Phone","AliExpress", 9 , List.of("/products/7.jpg")),
                    new ProductEntity("Y9 pro headset",14000.0,"Storage 128gb", 3.0,"Audio","Amezon", 7 , List.of("/products/8.jpg")),
                    new ProductEntity("Iphone X",24000.0,"Storage 256gb",4.5, "Phone","Amezon", 10 , List.of("/products/9.jpg")),
                    new ProductEntity("Dell i5",150000.0,"Storage 256gb", 4.2,"Laptop","Amezon", 20 , List.of("/products/10.jpg")),
                    new ProductEntity("Iphone 16",120000.0,"Storage 256gb", 4.0,"Phone","AliExpress", 9, List.of("/products/1.jpg")),
                    new ProductEntity("JBL",14000.0,"Storage 128gb", 3.0,"Audio","Amezon", 7, List.of("/products/2.jpg"))
            );

            productRepository.saveAll(demoProducts);
            System.out.println("Seeded Demo products");
        }else {
            System.out.println("Product already Exsits! Skip seed");
        }
    }
}
