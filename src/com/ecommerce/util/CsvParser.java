package com.ecommerce.util;

import com.ecommerce.model.Product;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    /* Method to convert products.csv file into List of Products
        -Method Name
        -Arguments
        -return type
        -Access Modifier
     */

    public List<Product> getProductsFromCsv() throws IOException {
        List<Product> products = new ArrayList<>();
        File file = new File("/Users/VISHU/vishu/products.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String productData = br.readLine();

        while (productData != null) {
            String[] split = productData.split(",");
            Product product = new Product();
            product.setId(Integer.parseInt(split[0]))
                    .setName(split[1])
                    .setMaxRetailPrice(Integer.parseInt(split[2]))
                    .setDiscountPercentage(Float.parseFloat(split[3]))
                    .setAvailable(Boolean.parseBoolean(split[4]))
                    .setCompany(split[5])
                    .setCategory(split[6])
                    .setManufacturedYear(Integer.parseInt(split[7]));

            products.add(product);
            productData = br.readLine();
        }
        return products;
    }
}
