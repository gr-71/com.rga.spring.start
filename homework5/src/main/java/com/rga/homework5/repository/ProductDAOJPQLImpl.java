package com.rga.homework5.repository;

import com.rga.homework5.domain.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductDAOJPQLImpl implements ProductDAO {

    @PersistenceContext
    private final EntityManager EM;

    public ProductDAOJPQLImpl(EntityManager EM) {
        this.EM = EM;
    }

    @Override
    public List<Product> findAll() {
        return EM.createQuery
                ("SELECT products FROM Product products", Product.class)
                .getResultList();
    }

    @Override
    public Product findById(Long id) {
        return EM.createQuery
                ("SELECT product FROM Product product WHERE product.id = :id",
                        Product.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Product> findByTitle(String title) {
        return EM.createQuery
                ("SELECT product FROM Product product WHERE product.title LIKE :title",
                        Product.class)
                .setParameter("title", title).getResultList();
    }

    @Override
    public void save(Product product) {
        // Transaction
        EM.getTransaction().begin();
        EM.persist(product);
        EM.flush();
        EM.getTransaction().commit();
    }

    @Override
    public void update(Product product) {
        // Transaction
        EM.getTransaction().begin();
        EM.merge(product);
        EM.getTransaction().commit();
    }

    @Override
    public void delete(Product product) {
        // Transaction
        EM.getTransaction().begin();
        EM.remove(product);
        EM.getTransaction().commit();
    }

    @Override
    public Product findMinPrice() {
        return EM.createQuery
                (Product.PRODUCT_MIN_PRICE,Product.class)
                .getSingleResult();
    }

    @Override
    public Product findMaxPrice() {
        return EM.createQuery
                (Product.PRODUCT_MAX_PRICE,Product.class)
                .getSingleResult ();
    }

    @Override
    public List<Product> filterByMinPrice() {
        return EM.createQuery
                ("SELECT product FROM Product product ORDER BY price DESC",
                        Product.class)
                .getResultList();
    }

    @Override
    public List<Product> filterByMaxPrice() {
        return EM.createQuery
                ("SELECT product FROM Product product ORDER BY price ASC",
                        Product.class)
                .getResultList();
    }
}
