package com.example.webshop.presentation;

import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.CategoryService;
import com.example.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryPresentation {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ItemService service;
    @Autowired
    AccountSessionManager manager;


    @GetMapping("/category")
    public String getCategory(Model m){
        m.addAttribute("user", manager.getCurrentUser().getUsername());
        m.addAttribute("categories", categoryService.getAllCategories());
        return "category";
    }

    @GetMapping("/category/1")
    public String getHeadphones(Model m){
        m.addAttribute("items", service.findAllByCategoryId(1));
        m.addAttribute("title", "Tony's Webshop - Headphones");
        m.addAttribute("categoryid", "1");
        m.addAttribute("category", "Headphones");
        return "items";
    }
    @GetMapping("/category/2")
    public String getSpeakers(Model m){
        m.addAttribute("items", service.findAllByCategoryId(2));
        m.addAttribute("title", "Tony's Webshop - Speakers");
        m.addAttribute("categoryid", "2");
        m.addAttribute("category", "Speakers");
        return "items";
    }
    @GetMapping("/category/3")
    public String getComputers(Model m){
        m.addAttribute("items", service.findAllByCategoryId(3));
        m.addAttribute("title", "Tony's Webshop - Computers");
        m.addAttribute("categoryid", "3");
        m.addAttribute("category", "Computers");
        return "items";
    }
    @GetMapping("/category/4")
    public String getMonitors(Model m){
        m.addAttribute("items", service.findAllByCategoryId(4));
        m.addAttribute("title", "Tony's Webshop - Monitors");
        m.addAttribute("categoryid", "4");
        m.addAttribute("category", "Monitors");
        return "items";
    }
    @GetMapping("/category/5")
    public String getMice(Model m){
        m.addAttribute("items", service.findAllByCategoryId(5));
        m.addAttribute("title", "Tony's Webshop - Mice");
        m.addAttribute("categoryid", "5");
        m.addAttribute("category", "Mice");
        return "items";
    }
    @GetMapping("/category/6")
    public String getKeyboards(Model m){
        m.addAttribute("items", service.findAllByCategoryId(6));
        m.addAttribute("title", "Tony's Webshop - Keyboards");
        m.addAttribute("categoryid", "6");
        m.addAttribute("category", "Keyboards");
        return "items";
    }
    @GetMapping("/category/7")
    public String getLaptops(Model m){
        m.addAttribute("items", service.findAllByCategoryId(7));
        m.addAttribute("title", "Tony's Webshop - Laptops");
        m.addAttribute("categoryid", "7");
        m.addAttribute("category", "Laptops");
        return "items";
    }
    @GetMapping("/category/8")
    public String getPhones(Model m){
        m.addAttribute("items", service.findAllByCategoryId(8));
        m.addAttribute("title", "Tony's Webshop - Phones");
        m.addAttribute("categoryid", "8");
        m.addAttribute("category", "Phones");
        return "items";
    }
    @GetMapping("/category/9")
    public String getSmartwatches(Model m){
        m.addAttribute("items", service.findAllByCategoryId(9));
        m.addAttribute("title", "Tony's Webshop - Smartwatches");
        m.addAttribute("categoryid", "9");
        m.addAttribute("category", "Smartwatches");
        return "items";
    }
}
