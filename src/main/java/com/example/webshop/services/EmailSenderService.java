package com.example.webshop.services;

import com.example.webshop.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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

        try {
            javaMailSender.send(message);
        }catch (MailException e){
            e.printStackTrace();
        }
    }
    public String generateEmailBody() {
        Set<OrderItem> orderItems = service.getLatestOrderItems();
        int totalAmount = 0;
        StringBuilder builder = new StringBuilder();

        for (OrderItem item : orderItems) {
            builder.append(item.getItem().getName()).append(" x ").append(item.getQuantity()).append("\t").append(item.getItem().getPrice() * item.getQuantity()).append(" kr\n");
            totalAmount = (item.getItem().getPrice() * item.getQuantity());
        }
        return "Thank you for your order!" + "\nHi " + manager.getCurrentUser().getUsername()
                + ",\nYour order has been successffully recieved and is now being processed. below are the details of your purchase:\n\n"
                + builder + "\nTotal: " + totalAmount + " kr";
    }

}
