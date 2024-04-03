package com.example.webshop.repository;

import com.example.webshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemById(int id);
    List<CartItem> findCartItemsByCart_Account_Id(int id);
}
