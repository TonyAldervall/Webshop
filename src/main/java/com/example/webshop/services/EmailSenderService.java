package com.example.webshop.services;

import com.example.webshop.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    OrderService service;
    @Autowired
    AccountSessionManager manager;

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tony.aldervall@iths.se");
        message.setTo(manager.getCurrentUser().getEmail());
        message.setSubject("Order Confirmation");
        message.setText(generateEmailBody());

        javaMailSender.send(message);
    }
    public String generateEmailBody(){
        Set<OrderItem> orderItems = service.getLatestOrderItems();
        int totalAmount = 0;
        for(OrderItem item : orderItems){
            totalAmount = (item.getItem().getPrice() * item.getQuantity());
        }
        return "Thank you for your order!" + "/nHi " + manager.getCurrentUser().getUsername()
                + ",/nYour order has been successffully recieved and is now being processed. below are the details of your purchase:"
                + orderItems.toString() + "/nTotal: " + totalAmount + " kr";
    }
}
