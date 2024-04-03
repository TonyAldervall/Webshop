package com.example.webshop.presentation;

import com.example.webshop.entity.Item;
import com.example.webshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ItemPresentation {
    @Autowired
    ItemService itemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AccountService accountService;

    @GetMapping("/category/{category}/{itemId}")
    public String getItemPage(@PathVariable String category, @PathVariable int itemId, Model m){
        if(accountService.sessionNull()){
            return "redirect:../../login";
        }
        m.addAttribute("confirm", "");
        return itemService.getPageInfo(category, itemId, m);
    }
    @PostMapping("/category/{category}/{itemId}")
    public String addToCart(@PathVariable String category, @PathVariable int itemId, @RequestParam("quantity") int quantity, Model m){
        if(cartItemService.addItemToCart(itemId, quantity)){
            m.addAttribute("confirm", "Item added to cart!");
        }
        else{
            m.addAttribute("confirm", "Item is already in cart!");
        }
        return itemService.getPageInfo(category, itemId, m);
    }
    @PostMapping("/search")
    public String search(@RequestParam String search, Model m) {
        List<Item> searchResult = itemService.itemContainsSearch(search);
        if(searchResult.isEmpty()){
        m.addAttribute("noresult", "No items matched the search.");
        m.addAttribute("items", null);
        }
        else{
            m.addAttribute("noresult", null);
            m.addAttribute("items", searchResult);
        }
        return "search";
    }
}
