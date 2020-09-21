package com.rga.homework5.controllers;

import com.rga.homework5.config.SpringDataConfig;
import com.rga.homework5.domain.Product;
import com.rga.homework5.repository.ProductJpaDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

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
