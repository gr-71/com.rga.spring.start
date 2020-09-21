package com.rga.homework5.domain;

import javax.persistence.*;

@Entity
@Table (name = "products")
@NamedQueries ({
        @NamedQuery(name = Product.PRODUCT_MIN_PRICE,
                query = "SELECT product.name FROM Product product ORDER BY product.price DESC"),
        @NamedQuery(name = Product.PRODUCT_MAX_PRICE,
                query = "SELECT product.name FROM Product product ORDER BY product.price ASC")
})
public class Product {

    public static final String PRODUCT_MIN_PRICE = "Product.minPrice";
    public static final String PRODUCT_MAX_PRICE = "Product.maxPrice";

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    @Column (name = "product_id")
    private Long id;

    @Column (name = "title_fld")
    private String title;

    @Column (name = "price_fld")
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
