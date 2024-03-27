package com.example.webshop.database;

import com.example.webshop.services.Cart;
import com.example.webshop.services.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

}
