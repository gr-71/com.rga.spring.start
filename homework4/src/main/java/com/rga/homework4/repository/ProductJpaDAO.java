package com.rga.homework4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rga.homework4.domain.Product;

import java.util.List;

public interface ProductJpaDAO extends JpaRepository<Product, Long> {
    List<Product> findAllByTitleLike(String title);
    List<Product> findAllByIdBetween(Long startId, Long endId);

}
