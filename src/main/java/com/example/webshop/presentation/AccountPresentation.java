package com.example.webshop.presentation;

import com.example.webshop.services.AccountService;
import com.example.webshop.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountPresentation {
    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String getRoot(){
        if(accountService.sessionNull()){
            return "redirect:login";
        }
        else{
            return "redirect:category";
        }
    }
    @GetMapping("/login")
    public String getLogin(Model m){
        if(accountService.sessionNull()){
            m.addAttribute("errormessage", "");
            return "login";
        }
        else{
            return "redirect:category";
        }
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email, @RequestParam String password, Model m){
        if(accountService.login(email, password)){
            return "redirect:category";
        }
        else {
            m.addAttribute("errormessage", "Wrong email or password.");
            return "login";
        }

    }
    @GetMapping("/logout")
    public String logout(){
        accountService.logout();
        return "redirect:login";
    }

    @GetMapping("/createaccount")
    public String getCreateAccount(Model m){
        m.addAttribute("errormessage", "");
        return "createaccount";
    }

    @PostMapping("/createaccount")
    public String postCreateAccount(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam(name = "confirmpassword") String confirmPassword, Model m){
        if(!password.equals(confirmPassword)){
            m.addAttribute("errormessage", "Password doesn't match.");
            return "createaccount";
        } else if (accountService.accountExists(email)) {
            m.addAttribute("errormessage", "Email is already in use.");
            return "createaccount";
        } else {
            accountService.createAccount(email, username, password);
            m.addAttribute("errormessage", "");
            return "login";
        }
    }
}
