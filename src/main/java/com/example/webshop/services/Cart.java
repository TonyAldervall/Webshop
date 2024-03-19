package com.example.webshop.services;

import jakarta.persistence.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Entity
@Table(name="cart")
@SessionScope
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Product> productList;
    @OneToOne
    private Account account;

    public Cart(Account account){
        this.account = account;
    }
    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productList=" + productList +
                '}';
    }
}
