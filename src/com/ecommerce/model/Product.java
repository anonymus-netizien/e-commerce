package com.ecommerce.model;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private int maxRetailPrice;
    private float discountPercentage;
    private int rating;
    private boolean isAvailable;
    private String company;
    private String category;
    private int manufacturedYear;

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

    public String getCompany() {
        return company;
    }

    public Product setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }

    public Product setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
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
        return id == product.id && maxRetailPrice == product.maxRetailPrice && Float.compare(discountPercentage, product.discountPercentage) == 0 && rating == product.rating && manufacturedYear == product.manufacturedYear && isAvailable == product.isAvailable && Objects.equals(name, product.name) && Objects.equals(company, product.company) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxRetailPrice, discountPercentage, rating, company, category, manufacturedYear, isAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxRetailPrice=" + maxRetailPrice +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", company='" + company + '\'' +
                ", category='" + category + '\'' +
                ", manufacturedYear=" + manufacturedYear +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
