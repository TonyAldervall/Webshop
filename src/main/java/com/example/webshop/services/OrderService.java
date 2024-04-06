package com.example.webshop.services;

import com.example.webshop.entity.Account;
import com.example.webshop.entity.CartItem;
import com.example.webshop.entity.Order;
import com.example.webshop.entity.OrderItem;
import com.example.webshop.repository.AccountRepo;
import com.example.webshop.repository.CartItemRepo;
import com.example.webshop.repository.OrderItemRepo;
import com.example.webshop.repository.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    AccountSessionManager manager;
    @Transactional
    public void placeOrder(){
        createOrder();
        convertCartItemToOrderItem();
        clearCart();
    }
    public void createOrder(){
        Account currentUser = manager.getCurrentUser();
        Order newOrder = new Order(currentUser);
        currentUser.getOrder().add(newOrder);
        orderRepo.save(newOrder);
    }
    public void convertCartItemToOrderItem(){
        List<CartItem> currentCartItems = cartItemRepo.findCartItemsByCart_Account_Id(manager.getCurrentUser().getId());
        Order latestOrder = orderRepo.findFirstByAccount_IdOrderByIdDesc(manager.getCurrentUser().getId());

        for(CartItem cartItem : currentCartItems){
            OrderItem orderItem = new OrderItem(latestOrder, cartItem.getItem(), cartItem.getQuantity());
            latestOrder.getOrderItemList().add(orderItem);
            orderItemRepo.save(orderItem);
        }
    }
    public void clearCart(){
        Set<CartItem> currentCartItems = manager.getCurrentUser().getCart().getCartItems();
        cartItemRepo.deleteAllInBatch(currentCartItems);
        currentCartItems.clear();
    }
    public Set<OrderItem> getLatestOrderItems(){
        Order latestOrder = orderRepo.findFirstByAccount_IdOrderByIdDesc(manager.getCurrentUser().getId());
        return latestOrder.getOrderItemList();
    }
    public long getTotalSum(){
        Set<OrderItem> orderItems = getLatestOrderItems();
        long totalSum = 0;

        for(OrderItem orderItem : orderItems){
            totalSum += ((long) orderItem.getItem().getPrice() * orderItem.getQuantity());
        }
        return totalSum;
    }
}
