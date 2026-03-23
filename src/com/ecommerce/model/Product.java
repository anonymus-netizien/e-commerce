package com.ecommerce.model;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private int maxRetailPrice;
    private float discountPercentage;
    private int rating;
    private boolean isAvailable;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public int getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public Product setMaxRetailPrice(int maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
        return this;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public Product setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Product setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Product setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && maxRetailPrice == product.maxRetailPrice && Float.compare(discountPercentage, product.discountPercentage) == 0 && rating == product.rating && isAvailable == product.isAvailable && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxRetailPrice, discountPercentage, rating, isAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxRetailPrice=" + maxRetailPrice +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
