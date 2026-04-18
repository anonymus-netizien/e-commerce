package com.ecommerce.main;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.CustomerServiceImpl;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

public class Main {

    //List Printing Method using printf
    private static void printHeader() {
        System.out.printf("%-5s %-30s %-10s %-10s %-18s %-18s %-8s%n",
                "ID", "Name", "Price", "Discount", "Company", "Category", "Avail");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private static void printProduct(Product product) {
        System.out.printf("%-5d %-30s %-10d %-10.1f %-18s %-18s %-8b%n",
                product.getId(),
                product.getName(),
                product.getMaxRetailPrice(),
                product.getDiscountPercentage(),
                product.getCompany(),
                product.getCategory(),
                product.isAvailable());
    }

    private static void printProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        printHeader();
        for (Product product : products) {
            printProduct(product);
        }
        System.out.println();
        System.out.println();
    }

    private static void printCustomerHeader() {
        System.out.printf("%-5s %-20s %-35s %-5s %-10s %-15s %-12s %-20s %-20s%n",
                "ID", "Name", "Email", "Age", "Gender", "Status", "Membership", "Residential City", "Shipping City");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void printCustomer(Customer customer) {
        System.out.printf("%-5d %-20s %-35s %-5d %-10s %-15s %-12s %-20s %-20s%n",
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAge(),
                customer.getGender(),
                customer.getStatus(),
                customer.getMembershipType(),
                customer.getResdentialAddress().getCity(),
                customer.getShippingAddress().getCity());
    }

    private static void printCustomers(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        printCustomerHeader();
        for (Customer customer : customers) {
            printCustomer(customer);
        }
        System.out.println();
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
//        ProductRepository productRepository = new ProductRepository();
//        List<Product> products = productRepository.getAll();
//
//        //List before Manipulation
//        for (Product product : products) {
//            System.out.println(product);
//        }
//
//        System.out.println();
//        System.out.println();
//
//        //Repository Operations
//        //get product by ID
//        System.out.println(productRepository.getById(1));
//
//        //product update
//        System.out.println(productRepository.update(new Product().setId(1).setName("MacBook Pro").setMaxRetailPrice(169999).setDiscountPercentage(10.7f).setRating(4).setAvailable(true)));
//        System.out.println(productRepository.update(7, new Product().setId(7).setName("Laptop").setMaxRetailPrice(139999).setDiscountPercentage(10.0f).setRating(4).setAvailable(true)));
//
//        //save product
//        productRepository.save(new Product().setId(101).setName("Wireless Mouse").setMaxRetailPrice(799).setDiscountPercentage(10.5f).setRating(4).setAvailable(true));
//
//        //Delete product
//        productRepository.delete(6);
//        System.out.println();
//        System.out.println();
//
//        //new updated list
//        for (Product product : products) {
//            System.out.println(product);
//        }

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductServiceImpl(productRepository);

        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerServiceImpl(customerRepository);

        System.out.println("---------------------------------------- Basic Level ---------------------------------------- ");

        printProducts(productService.getAllProductsByAvailability(true));

        printProducts(productService.getProductsByCategory("Clothing"));

        printProducts(productService.getProductsByPriceGreaterThan(50000));

        System.out.println(productService.getAllProductsName());

        System.out.println(productService.getTotalProductsCount());

        System.out.println(productService.existsProductsByCompany("Apple"));

        System.out.println(productService.areAllProductsAvailable());

        productService.getFirstProduct()
                .ifPresent(Main::printProduct);

        System.out.println("---------------------------------------- Intermediate Level ---------------------------------------- ");

        System.out.println(productService.getDistinctCategories());

        printProducts(productService.getTopNMostExpensiveProducts(6));

        printProducts(productService.getProductsSortedByPriceAsc());

        printProducts(productService.getProductsSortedByNameDesc());

        System.out.println(productService.calculateTotalInventoryValue());

        System.out.println(productService.calculateTotalFinalPrice(productRepository.getAll()));

        printProducts(productService.getProductsManufacturedAfter(2022));

        printProducts(productService.getAvailableProductsWithPriceGreaterThan(100000));

        System.out.println(productService.getProductsCountFromCategory());

        System.out.println("---------------------------------------- Advance Level ---------------------------------------- ");

        System.out.println(productService.getProductsGroupedByCategory());

        System.out.println(productService.getProductsGroupedByCompany());

        System.out.println(productService.getProductsPartitionedByAvailability());

        System.out.println(productService.getProductWithHighestPrice());
        printProducts(productService.getProductsWithHighestPrice());

        System.out.println(productService.getProductWithLowestPrice());
        printProducts(productService.getProductsWithLowestPrice());

        System.out.println(productService.getProductById(4));
        System.out.println(productService.getProductMapById());

        System.out.println(productService.getAveragePriceByCategory());

        System.out.println(productService.getTopThreeMostExpensiveProductsByCategory());


        printCustomers(customerService.getAllCustomers());



    }
}
