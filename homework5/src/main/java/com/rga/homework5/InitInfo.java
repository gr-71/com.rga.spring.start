package com.rga.homework5;

import com.rga.homework5.domain.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class InitInfo {

    public static final double PRODUCTS_COUNTING = 20;
    private static ArrayList<Product> products = new ArrayList<>();

    private static void initProducts(EntityManager em) {
        // Transaction
        em.getTransaction().begin();
        for (Product product : products) {
            em.merge(product);
        }
        System.out.println("Our products were initialized successfully.");
        em.getTransaction().commit();
    }

    public InitInfo() {
    }

    private static void initInfo(EntityManager em){
        initProducts(em);
    }

    // to generate products' titles like "item N ..." with their prices
    static {
        int n = 0;
        for (int c = 0; c < PRODUCTS_COUNTING; c++) {
            Product product = new Product ();
            product.setTitle ("item N " + n++);
            product.setPrice (10 + (Math.random() * 300));
            products.add (product);
        }
    }

//    public static ArrayList<Product> getProducts() {
//        return products;
//    }
//
//    public static void setProducts(ArrayList<Product> products) {
//        InitInfo.products = products;
//    }
}
