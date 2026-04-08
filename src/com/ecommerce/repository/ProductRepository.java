package com.ecommerce.repository;

import com.ecommerce.model.Product;
import com.ecommerce.util.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() throws IOException {
        CsvParser csvParser = new CsvParser();
        products = csvParser.getProductsFromCsv();
    }

    //Get all Products
    public List<Product> getAll() {
        return this.products;
    }

    //Get Product by ID
    public Optional<Product> getById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    //Save a new Product
    public Product save(Product product) {
        this.products.add(product);
        return product;
    }

    //Update a Product
    public Product update(int id, Product product) {
        this.products.replaceAll(p -> p.getId() == id ? product : p);
        return product;
    }

    //Delete a Product
    public boolean delete(int id) {
        this.products.removeIf(product -> product.getId() == id);
        return true;
    }
    //a Flavour of delete which depends on overriding of equals and hashcode
//    public boolean delete(Product product) {
//        this.products.remove(product);
//        return true;
//    }

}
