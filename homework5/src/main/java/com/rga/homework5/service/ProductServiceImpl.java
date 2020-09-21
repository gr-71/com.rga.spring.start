package com.rga.homework5.service;

import com.rga.homework5.domain.Product;
import com.rga.homework5.repository.ProductJpaDAO;
import com.rga.homework5.repository.ProductDAOJPQLImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl {

    private final ProductJpaDAO PRODUCT_JPA_DAO;
    private final ProductDAOJPQLImpl PRODUCT_DAO_JPQL_IMPL;

    public ProductServiceImpl(ProductJpaDAO productJpaDAO, ProductDAOJPQLImpl PRODUCT_DAO_JPQL_IMPL) {
        this.PRODUCT_JPA_DAO = productJpaDAO;
        this.PRODUCT_DAO_JPQL_IMPL = PRODUCT_DAO_JPQL_IMPL;
    }

    @Transactional
    public void save(Product product){
        Product savedProduct = PRODUCT_JPA_DAO.save(product);
    }

    @Transactional
    public void delete(Long id){
        PRODUCT_JPA_DAO.deleteById (id);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id){
        return PRODUCT_JPA_DAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public String findTitleById(Long id){
        return PRODUCT_JPA_DAO.findById(id).orElse(null).getTitle ();
    }
}
