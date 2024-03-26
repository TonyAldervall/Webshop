package com.example.webshop.services;

import com.example.webshop.database.CartItemRepo;
import com.example.webshop.database.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo repo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    AccountSessionManager manager;
    public void addItemToCart(int itemId, int quantity){
        CartItem cartItem = new CartItem(manager.getCurrentUser().getCart(), itemRepo.findById(itemId), quantity);
        repo.save(cartItem);
    }
}
