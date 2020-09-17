package com.rga.homework4.repository;

import com.rga.homework4.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Long id);
    void saveAndSet(Product product);
    void update(Product product);
    void delete(Product product);
}
