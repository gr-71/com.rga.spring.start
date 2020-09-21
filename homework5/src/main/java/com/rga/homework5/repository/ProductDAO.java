package com.rga.homework5.repository;

import com.rga.homework5.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByTitle(String title);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Product findMinPrice();
    Product findMaxPrice();
    List<Product> filterByMinPrice();
    List<Product> filterByMaxPrice();
}





