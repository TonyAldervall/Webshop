package com.example.webshop.presentation;

import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.CategoryService;
import com.example.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/category/{category}")
    public String getItems(@PathVariable("category") String category, Model m){
        int categoryId = categoryService.getCategoryId(category);
        String categoryName = categoryService.getCategoryName(categoryId);
        if(categoryId == -1){
            System.out.println("Couldn't find category for some reason.");
        }

        m.addAttribute("items", service.findAllByCategoryId(categoryId));
        m.addAttribute("title", "Tony's Webshop - " + categoryName);
        m.addAttribute("header", categoryName);
        return "items";
    }
}
