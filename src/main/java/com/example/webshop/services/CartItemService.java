package com.example.webshop.services;

import com.example.webshop.entity.Cart;
import com.example.webshop.entity.CartItem;
import com.example.webshop.repository.AccountRepo;
import com.example.webshop.repository.CartItemRepo;
import com.example.webshop.repository.CartRepo;
import com.example.webshop.repository.ItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    AccountRepo accountRepo;
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
    public List<CartItem> getMyCartItems(){
        return cartItemRepo.findCartItemsByCart_Account_Id(manager.getCurrentUser().getId());
    }
    public long getTotalSum(){
        List<CartItem> cartItems = getMyCartItems();
        long totalSum = 0;

        for(CartItem c : cartItems){
            totalSum += ((long) c.getItem().getPrice() * c.getQuantity());
        }
        return totalSum;
    }
    public void increaseQuantity(String id){
        CartItem cartItem = cartItemRepo.findCartItemById(Integer.parseInt(id));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepo.save(cartItem);
    }
    public void decreaseQuantity(String id){
        CartItem cartItem = cartItemRepo.findCartItemById(Integer.parseInt(id));
        if(!(cartItem.getQuantity() <= 1)){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepo.save(cartItem);
        }

    }
    @Transactional
    public void removeCartItem(String id){
        Cart currentCart = manager.getCurrentUser().getCart();
        CartItem cartItemToRemove = null;

        for(CartItem cartItem : currentCart.getCartItems()){
            if(cartItem.getId() == Integer.parseInt(id)){
                cartItemToRemove = cartItem;
                break;
            }
        }

        if (cartItemToRemove != null) {
            currentCart.getCartItems().remove(cartItemToRemove);
            cartItemRepo.delete(cartItemToRemove);

        } else {
            System.out.println("CartItem with ID " + id + " not found in the currentCart.");
        }
    }
}
