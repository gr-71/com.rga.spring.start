package com.rga.homework4;

import com.rga.homework4.config.SpringDataConfig;
import com.rga.homework4.domain.Product;
import com.rga.homework4.repository.ProductJpaDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext
                (SpringDataConfig.class);

        ProductJpaDAO productJpaDAO = applicationContext.getBean(ProductJpaDAO.class);

        Product product = new Product("Rice", 50.35);

        productJpaDAO.save(product);
    }
}
