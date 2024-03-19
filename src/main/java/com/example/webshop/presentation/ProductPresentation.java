package com.example.webshop.presentation;

import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductPresentation {
    @Autowired
    ProductService service;
    @Autowired
    AccountSessionManager manager;


    @GetMapping("/categories")
    public String getCategories(Model m){
        m.addAttribute("user", manager.getCurrentUser().getUsername());
        return "categories";
    }
}
