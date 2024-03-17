package com.example.webshop.presentation;

import com.example.webshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountPresentation {
    @Autowired
    AccountService service;
    @GetMapping("/login")
    public String getLogin(Model m){
        m.addAttribute("errormessage", "");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password, Model m){

        return "login";
    }
}
