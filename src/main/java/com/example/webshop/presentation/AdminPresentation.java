package com.example.webshop.presentation;

import com.example.webshop.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPresentation {
    @Autowired
    AdminService service;

    @GetMapping("/adminlogin")
    public String getLogin(Model m) {
        if(service.sessionNull()) {
            m.addAttribute("errormessage", "");
            return "adminlogin";
        }
        else{
            return "redirect:adminhome";
        }
    }

    @PostMapping("/adminlogin")
    public String postLogin(@RequestParam String username, @RequestParam String password, Model m) {
        if (service.adminLogin(username, password)) {
            return "redirect:adminhome";
        } else {
            m.addAttribute("errormessage", "Wrong username or password.");
            return "adminlogin";
        }
    }
    @GetMapping("/adminlogout")
    public String logout() {
        service.adminLogout();
        return "redirect:adminlogin";
    }
    @GetMapping("/adminhome")
    public String home(){
        if(service.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            return "adminhome";
        }
    }
    @GetMapping("/additem")
    public String getAddItem(){
        if(service.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            return "adminadditem";
        }
    }
    @GetMapping("/manageorder")
    public String getManageOrder(){
        if(service.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            return "adminmanageorders";
        }
    }
}