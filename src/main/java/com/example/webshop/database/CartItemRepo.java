package com.example.webshop.database;

import com.example.webshop.services.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemById(int id);
    List<CartItem> findCartItemsByCart_Account_Id(int id);
    void removeCartItemById(int id);
}
