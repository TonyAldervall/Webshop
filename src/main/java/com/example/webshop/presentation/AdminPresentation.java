package com.example.webshop.presentation;

import com.example.webshop.entity.Category;
import com.example.webshop.services.AdminService;
import com.example.webshop.services.CategoryService;
import com.example.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPresentation {
    @Autowired
    AdminService adminService;
    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/adminlogin")
    public String getLogin(Model m) {
        if(adminService.sessionNull()) {
            m.addAttribute("errormessage", "");
            return "adminlogin";
        }
        else{
            return "redirect:adminhome";
        }
    }

    @PostMapping("/adminlogin")
    public String postLogin(@RequestParam String username, @RequestParam String password, Model m) {
        if (adminService.adminLogin(username, password)) {
            return "redirect:adminhome";
        } else {
            m.addAttribute("errormessage", "Wrong username or password.");
            return "adminlogin";
        }
    }
    @GetMapping("/adminlogout")
    public String logout() {
        adminService.adminLogout();
        return "redirect:adminlogin";
    }
    @GetMapping("/adminhome")
    public String home(){
        if(adminService.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            return "adminhome";
        }
    }
    @GetMapping("/createitem")
    public String getCreateItem(Model m){
        if(adminService.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            m.addAttribute("confirmmessage", "");
            m.addAttribute("categories", categoryService.getAllCategories());
            return "admincreateitem";
        }
    }
    @PostMapping("/createitem")
    public String postCreateItem(@RequestParam String name, @RequestParam int price, @RequestParam("category") String categoryName, Model m){
        int categoryId = categoryService.getCategoryId(categoryName.toLowerCase());
        Category category = categoryService.getCategoryById(categoryId);
        itemService.createItem(name, price, category);
        m.addAttribute("confirmmessage", "Item was successfully added!");
        return "admincreateitem";
    }
    @GetMapping("/manageorder")
    public String getManageOrder(){
        if(adminService.sessionNull()){
            return "redirect:adminlogin";
        }
        else {
            return "adminmanageorders";
        }
    }
}