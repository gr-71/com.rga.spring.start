package com.rga.homework4.domain;

import javax.persistence.*;

@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue

    @Column (name = "product_id")
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "price")
    private Double price;

    public Product() {
    }

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product [ id = %d; title = %s; price = %d]", id, title, price);
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
