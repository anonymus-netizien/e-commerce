package com.ecommerce.service;

import com.ecommerce.exception.ProductExistsException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    //Dependency
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Overriding Repository Methods
    @Override
    public Product save(Product product) throws ProductExistsException {
        //Checks if Product already exists by id
        productRepository.getById(product.getId()).ifPresent(p -> {throw new ProductExistsException("Product with id " + product.getId() + " already exists");});
        return productRepository.save(product);

    }

    @Override
    public Product getById(int id) throws ProductNotFoundException {
        return productRepository.getById(id).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product update(int id, Product product) throws ProductNotFoundException {
        productRepository.getById(id).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
        return productRepository.update(id, product);
    }

    @Override
    public void delete(int id) throws ProductNotFoundException {
        productRepository.getById(id).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(id);
    }

    @Override
    public List<Product> getAllProductsByAvailability(boolean available) {
        return productRepository.getAll()
                .stream()
                .filter(product -> product.isAvailable() == available)
                .toList();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getAll()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    @Override
    public List<Product> getProductsByPriceGreaterThan(int price) {
        return productRepository.getAll()
                .stream()
                .filter(product -> product.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public List<String> getAllProductsName() {
        return productRepository.getAll()
                .stream()
                .map(Product::getName)
                .toList();
    }

    @Override
    public long getTotalProductsCount() {
        return productRepository.getAll()
                .stream()
                .filter(Product::isAvailable)
                .count();
    }

    @Override
    public boolean existsProductsByCompany(String company) {
        return productRepository.getAll()
                .stream()
                .anyMatch(product -> product.getCompany().equals(company));
    }

    @Override
    public boolean areAllProductsAvailable() {
        return productRepository.getAll()
                .stream()
                .allMatch(Product::isAvailable);
    }

    @Override
    public Optional<Product> getFirstProduct() {
        return productRepository.getAll()
                .stream()
                .findFirst();
    }

    @Override
    public List<String> getDistinctCategories() {
        return productRepository.getAll()
                .stream()
                .map(Product::getCategory)
                .map(String::toLowerCase)
                .distinct()
                .toList();
    }

    @Override
    public List<Product> getTopNMostExpensiveProducts(int limit) {
        return productRepository.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Product::getMaxRetailPrice).reversed())
                .limit(limit)
                .toList();
    }

    @Override
    public List<Product> getProductsSortedByPriceAsc() {
        return productRepository.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Product::getMaxRetailPrice))
                .toList();
    }

    @Override
    public List<Product> getProductsSortedByNameDesc() {
        return productRepository.getAll()
                .stream()
                .sorted(Comparator.comparing(p -> p.getName().toLowerCase(), Comparator.reverseOrder()))
                .toList();
    }

    @Override
    public double calculateTotalInventoryValue() {
        return productRepository.getAll()
                .stream()
                //.filter(Product::isAvailable)
                .collect(Collectors.summingDouble(Product::getMaxRetailPrice));
    }

    @Override
    public BigDecimal calculateFinalPrice(Product product) {
        BigDecimal originalPrice = BigDecimal.valueOf(product.getMaxRetailPrice());
        BigDecimal discountPercentage = BigDecimal.valueOf(product.getDiscountPercentage());

        BigDecimal discountAmount = originalPrice.multiply(discountPercentage)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return originalPrice.subtract(discountAmount);
    }

    @Override
    public BigDecimal calculateTotalFinalPrice(List<Product> products) {
        return products.stream()
                .map(this::calculateFinalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Product> getProductsManufacturedAfter(int year) {
        return productRepository.getAll()
                .stream()
                .filter(product -> product.getManufacturedYear() > year)
                .toList();
    }

    @Override
    public List<Product> getAvailableProductsWithPriceGreaterThan(double price) {
        return productRepository.getAll()
                .stream()
                .filter(product -> product.getMaxRetailPrice() > price)
                .toList();
    }

    @Override
    public Map<String, Long> getProductsCountFromCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    @Override
    public Map<String, List<Product>> getProductsGroupedByCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.toList()));
    }

    @Override
    public Map<String, List<Product>> getProductsGroupedByCompany() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCompany, Collectors.toList()));
    }

    @Override
    public Map<Boolean, List<Product>> getProductsPartitionedByAvailability() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.partitioningBy(Product::isAvailable));
    }

    @Override
    public Optional<Product> getProductWithHighestPrice() {
        return productRepository.getAll()
                .stream()
                .max(Comparator.comparingInt(Product::getMaxRetailPrice));
    }

    @Override
    public List<Product> getProductsWithHighestPrice() {
        int maxPrice = productRepository.getAll().stream()
                .mapToInt(Product::getMaxRetailPrice)
                .max()
                .orElse(0);

        return productRepository.getAll().stream()
                .filter(product -> product.getMaxRetailPrice() == maxPrice)
                .toList();
    }

    @Override
    public Optional<Product> getProductWithLowestPrice() {
        return productRepository.getAll()
                .stream()
                .min(Comparator.comparingInt(Product::getMaxRetailPrice));
    }

    @Override
    public List<Product> getProductsWithLowestPrice() {
        int minPrice = productRepository.getAll().stream()
                .mapToInt(Product::getMaxRetailPrice)
                .min()
                .orElse(0);

        return productRepository.getAll().stream()
                .filter(product -> product.getMaxRetailPrice() == minPrice)
                .toList();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public Map<Integer, Product> getProductMapById() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.toMap(Product::getId, p -> p));
    }

    @Override
    public Map<String, BigDecimal> getAveragePriceByCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.averagingInt(Product::getMaxRetailPrice),
                                //BigDecimal::valueOf
                                avg -> BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP)
                        )
                ));
    }

    @Override
    public Map<String, List<Product>> getTopThreeMostExpensiveProductsByCategory() {
        return productRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(
                        Collectors.toList(),list -> list.stream()
                                .sorted(Comparator.comparingInt(Product::getMaxRetailPrice).reversed())
                                .limit(3)
                                .toList()
                )));
    }


}