package com.example.webshop.services;

import com.example.webshop.database.CartItemRepo;
import com.example.webshop.database.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    AccountSessionManager manager;
    public boolean addItemToCart(int itemId, int quantity){
        Cart currentCart = manager.getCurrentUser().getCart();

        for(CartItem cartItem : currentCart.getCartItems()){
            if(cartItem.getItem().getId() == itemId){
                return false;
            }
        }

        CartItem cartItem = new CartItem(currentCart, itemRepo.findById(itemId), quantity);
        currentCart.getCartItems().add(cartItem);
        cartItemRepo.save(cartItem);
        return true;

    }
}
