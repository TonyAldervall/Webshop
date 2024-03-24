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

    @GetMapping("/category/1/2")
    public String getMajorIV(Model m){
        m.addAttribute("title", "Tony's Webshop - Marshall Major IV");
        return "itempage";
    }
}
