package com.example.webshop.database;

import com.example.webshop.services.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemByItem_Id(int id);
    CartItem findCartItemByCart_Account_Id(int id);
}
