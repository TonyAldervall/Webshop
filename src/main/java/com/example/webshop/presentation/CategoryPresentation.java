package com.example.webshop.presentation;

import com.example.webshop.services.AccountService;
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
    ItemService itemService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountSessionManager manager;


    @GetMapping("/category")
    public String getCategory(Model m){
        if(accountService.sessionNull()){
            return "redirect:login";
        }
        m.addAttribute("user", manager.getCurrentUser().getUsername());
        m.addAttribute("categories", categoryService.getAllCategories());
        return "category";
    }
    @GetMapping("/category/{category}")
    public String getItems(@PathVariable("category") String categoryLowercase, Model m){
        if(accountService.sessionNull()){
            return "redirect:../login";
        }
        int categoryId = categoryService.getCategoryId(categoryLowercase);
        String categoryName = categoryService.getCategoryCapitalized(categoryLowercase);

        m.addAttribute("items", itemService.findAllByCategoryId(categoryId));
        m.addAttribute("title", "Tony's Webshop - " + categoryName);
        m.addAttribute("header", categoryName);
        return "items";
    }
}
