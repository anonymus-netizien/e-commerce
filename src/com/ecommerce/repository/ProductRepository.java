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

    //Save a new Product
    public Product save(Product product) {
        this.products.add(product);
        return product;
    }

    //Get Product by ID
    public Optional<Product> getById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

}
