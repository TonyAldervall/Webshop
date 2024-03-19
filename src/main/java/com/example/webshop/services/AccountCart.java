package com.example.webshop.services;

import jakarta.persistence.*;

@Entity
@Table(name="account_cart")
public class AccountCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;
    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    public AccountCart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "AccountCart{" +
                "id=" + id +
                ", account=" + account +
                ", cart=" + cart +
                '}';
    }
}
