package com.example.webshop.presentation;

import com.example.webshop.services.AccountService;
import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.CartItemService;
import com.example.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartPresentation {
    @Autowired
    CartItemService itemService;
    @Autowired
    AccountService accountService;

    @GetMapping("/cart")
    public String getCart(Model m){
        if(accountService.sessionNull()){
            return "redirect:login";
        }
        if(itemService.getMyCartItems().isEmpty()){
            m.addAttribute("emptycart", "Cart is empty, add an item before proceeding to checkout!");
        }else {
            m.addAttribute("emptycart", "");
        }
        m.addAttribute("cartitems", itemService.getMyCartItems());
        m.addAttribute("totalsum", itemService.getTotalSum());
        return "cart";
    }
    @PostMapping("/increase")
    public String increase(@RequestParam(name = "cartitemid") String cartItemId){
        itemService.increaseQuantity(cartItemId);
        return "redirect:cart";
    }
    @PostMapping("/decrease")
    public String decrease(@RequestParam(name = "cartitemid") String cartItemId){
        itemService.decreaseQuantity(cartItemId);
        return "redirect:cart";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam(name = "cartitemid") String cartItemId){
        itemService.removeCartItem(cartItemId);
        return "redirect:cart";
    }
}
