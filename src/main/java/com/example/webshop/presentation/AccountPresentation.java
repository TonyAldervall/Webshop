package com.example.webshop.presentation;

import com.example.webshop.services.AccountService;
import com.example.webshop.services.AccountSessionManager;
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
    @Autowired
    AccountSessionManager manager;

    @GetMapping("/")
    public String getRoot(Model m){
        if(service.sessionNull()){
            return "redirect:/login";
        }
        else{
            return "redirect:/category";
        }
    }
    @GetMapping("/login")
    public String getLogin(Model m){
        if(service.sessionNull()){
            m.addAttribute("errormessage", "");
            return "/login";
        }
        else{
            return "redirect:/category";
        }
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email, @RequestParam String password, Model m){
        if(service.login(email, password)){
            return "redirect:/category";
        }
        else {
            m.addAttribute("errormessage", "Wrong email or password.");
            return "/login";
        }

    }

    @GetMapping("/createaccount")
    public String getCreateAccount(Model m){
        m.addAttribute("errormessage", "");
        return "/createaccount";
    }

    @PostMapping("/createaccount")
    public String postCreateAccount(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam(name = "confirmpassword") String confirmPassword, Model m){
        if(!password.equals(confirmPassword)){
            m.addAttribute("errormessage", "Password doesn't match.");
            return "/createaccount";
        } else if (service.accountExists(email)) {
            m.addAttribute("errormessage", "Email is already in use.");
            return "/createaccount";
        } else {
            service.createAccount(email, username, password);
            m.addAttribute("errormessage", "");
            return "/login";
        }
    }
}
