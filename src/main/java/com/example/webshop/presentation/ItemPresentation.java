package com.example.webshop.presentation;

import com.example.webshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemPresentation {
    @Autowired
    ItemService itemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AccountSessionManager manager;

    @GetMapping("/category/{category}/{itemId}")
    public String getItemPage(@PathVariable String category, @PathVariable int itemId, Model m){
        m.addAttribute("confirm", "");
        return itemService.getPageInfo(category, itemId, m);
    }
    @PostMapping("/category/{category}/{itemId}")
    public String addToCart(@PathVariable String category, @PathVariable int itemId, @RequestParam int quantity, Model m){
        cartItemService.addItemToCart(itemId, quantity);
        m.addAttribute("confirm", "Item added to cart!");
        return itemService.getPageInfo(category, itemId, m);
    }
}
