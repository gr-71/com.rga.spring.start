package com.rga.homework4.controllers;

import com.rga.homework4.config.SpringDataConfig;
import com.rga.homework4.domain.Product;

import com.rga.homework4.repository.ProductJpaDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/products")
public class ProdController {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext
            (SpringDataConfig.class);

    ProductJpaDAO productJpaDAO = applicationContext.getBean(ProductJpaDAO.class);

    @RequestMapping(method = RequestMethod.GET)
    public String list (Model model){
        List<Product> products = productJpaDAO.findAll();
        model.addAttribute("products", products);
        return "allProducts";
    }
}
