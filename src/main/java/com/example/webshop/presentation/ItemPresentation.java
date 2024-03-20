package com.example.webshop.presentation;

import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemPresentation {
    @Autowired
    ItemService service;
    @Autowired
    AccountSessionManager manager;


    @GetMapping("/category")
    public String getCategory(Model m){
        m.addAttribute("user", manager.getCurrentUser().getUsername());
        return "category";
    }
}
