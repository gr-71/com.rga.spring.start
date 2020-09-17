package com.rga.homework4.services;

import com.rga.homework4.domain.Product;
import com.rga.homework4.repository.ProductDAO;
import com.rga.homework4.repository.ProductJpaDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductDAO {

    private final ProductJpaDAO PRODUCT_JPA_DAO;

    public ProductServiceImpl(ProductJpaDAO PRODUCT_JPA_DAO) {
        this.PRODUCT_JPA_DAO = PRODUCT_JPA_DAO;
    }

    private long ind = 0;

    @Override
    @Transactional
    public void saveAndSet(Product product){
        if(product.getId() == null){
            product.setId(++ind);
        }
        PRODUCT_JPA_DAO.save(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        PRODUCT_JPA_DAO.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        PRODUCT_JPA_DAO.delete (product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return PRODUCT_JPA_DAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id){
        return PRODUCT_JPA_DAO.findById(id).orElse(null);
    }

}
