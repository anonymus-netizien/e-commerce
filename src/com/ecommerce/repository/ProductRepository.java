package com.ecommerce.repository;

import com.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>(List.of(
                new Product().setId(1).setName("Laptop").setMaxRetailPrice(75000).setDiscountPercentage(10.5f).setRating(4).setAvailable(true),
                new Product().setId(2).setName("Smartphone").setMaxRetailPrice(30000).setDiscountPercentage(15.0f).setRating(5).setAvailable(true),
                new Product().setId(3).setName("Headphones").setMaxRetailPrice(2500).setDiscountPercentage(20.0f).setRating(4).setAvailable(true),
                new Product().setId(4).setName("Smart Watch").setMaxRetailPrice(5000).setDiscountPercentage(12.5f).setRating(3).setAvailable(false),
                new Product().setId(5).setName("Tablet").setMaxRetailPrice(20000).setDiscountPercentage(18.0f).setRating(4).setAvailable(true),
                new Product().setId(6).setName("Bluetooth Speaker").setMaxRetailPrice(3500).setDiscountPercentage(25.0f).setRating(5).setAvailable(true),
                new Product().setId(7).setName("Gaming Console").setMaxRetailPrice(45000).setDiscountPercentage(8.0f).setRating(5).setAvailable(false),
                new Product().setId(8).setName("Keyboard").setMaxRetailPrice(1200).setDiscountPercentage(30.0f).setRating(3).setAvailable(true),
                new Product().setId(9).setName("Mouse").setMaxRetailPrice(800).setDiscountPercentage(35.0f).setRating(4).setAvailable(true),
                new Product().setId(10).setName("Monitor").setMaxRetailPrice(15000).setDiscountPercentage(10.0f).setRating(4).setAvailable(false)
        ));
    }

    //Get all Products
    public List<Product> getAll() {
        return products;
    }

    //Get a specific Product
    public Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    //update a Product
    public Product update(Product product) {
        Product oldProduct = getById(product.getId());
        if (oldProduct != null) {
            int oldProductId = products.indexOf(oldProduct);
            products.set(oldProductId, product);
            return product;
        }
        return null;
    }

//    public Product update(int id, Product product) {
//        Product oldProduct = getById(id);
//        if (oldProduct != null) {
//            int oldProductId = products.indexOf(oldProduct);
//            products.set(oldProductId, product);
//            return product;
//        }
//        return null;
//    }

    //create and save a new Product
    public void save(Product product) {
        if (getById(product.getId()) != null) { //checking if new given product exists, if true return null
            throw new RuntimeException("Product with ID " + product.getId() + " already exists");
        }
        products.add(product);
    }

    //
    public void delete(int id) {
        Product product = getById(id);
        if (product != null) {
            products.remove(product);
        }
    }

}
