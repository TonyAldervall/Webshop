package com.example.webshop.presentation;

import com.example.webshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderPresentation {
    @Autowired
    CartItemService itemService;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    EmailSenderService senderService;

    @GetMapping("/checkout")
    public String getCheckout(Model m){
        if(accountService.sessionNull()){
            return "redirect:login";
        }
        if(itemService.getMyCartItems().isEmpty()){
            return "redirect:cart";
        }
        m.addAttribute("cartitems", itemService.getMyCartItems());
        m.addAttribute("totalsum", itemService.getTotalSum());
        return "checkout";
    }
    @PostMapping("/order")
    public String postOrder(Model m){
        orderService.placeOrder();
        senderService.sendEmail(m);

        m.addAttribute("totalsum", orderService.getTotalSum());
        m.addAttribute("orderitems", orderService.getLatestOrderItems());
        return "order";
    }
}
