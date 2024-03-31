package com.example.webshop.presentation;

import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartPresentation {
    @Autowired
    CartItemService service;
    @Autowired
    AccountSessionManager manager;

    @GetMapping("/cart")
    public String getCart(Model m){
        m.addAttribute("cartitems", service.getMyCartItems());
        m.addAttribute("totalsum", service.getTotalSum());
        m.addAttribute("emptycart", "");
        return "cart";
    }
    @PostMapping("/increase")
    public String increase(@RequestParam(name = "cartitemid") String cartItemId){
        service.increaseQuantity(cartItemId);
        return "redirect:cart";
    }
    @PostMapping("/decrease")
    public String decrease(@RequestParam(name = "cartitemid") String cartItemId){
        service.decreaseQuantity(cartItemId);
        return "redirect:cart";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam(name = "cartitemid") String cartItemId){
        service.removeCartItem(cartItemId);
        return "redirect:cart";
    }
    @GetMapping("/checkout")
    public String getCheckout(Model m){
        m.addAttribute("cartitems", service.getMyCartItems());
        m.addAttribute("totalsum", service.getTotalSum());
        return "checkout";
    }
    @PostMapping("/order")
    public String postOrder(Model m){
        System.out.println("order placed :)");
        return "redirect:checkout";
    }
}
