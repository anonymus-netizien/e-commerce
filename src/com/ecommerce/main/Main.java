package com.ecommerce.main;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.getAll();

        //List before Manipulation
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println();
        System.out.println();

        //Repository Operations
        //get product by ID
        System.out.println(productRepository.getById(1));

        //product update
        System.out.println(productRepository.update(new Product().setId(1).setName("Macbook Pro").setMaxRetailPrice(169999).setDiscountPercentage(10.7f).setRating(4).setAvailable(true)));
//        System.out.println(productRepository.update(7, new Product().setId(7).setName("Laptop").setMaxRetailPrice(139999).setDiscountPercentage(10.0f).setRating(4).setAvailable(true)));

        //save product
        productRepository.save(new Product().setId(101).setName("Wireless Mouse").setMaxRetailPrice(799).setDiscountPercentage(10.5f).setRating(4).setAvailable(true));

        //Delete product
        productRepository.delete(6);
        System.out.println();
        System.out.println();

        //new updated list
        for (Product product : products) {
            System.out.println(product);
        }





    }
}
