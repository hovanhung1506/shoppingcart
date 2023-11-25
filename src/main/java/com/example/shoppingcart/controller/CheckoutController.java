package com.example.shoppingcart.controller;

import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CartEntity cartEntity;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("numberOfItems", cartEntity.count());
        return "checkout/index";
    }

    @PostMapping()
    public String checkout(RedirectAttributes redirect, @RequestParam String name, @RequestParam String address) {
        checkoutService.checkout(name, address);
        cartEntity.emptyCart();
        redirect.addFlashAttribute("checkout", true);
        return "redirect:/products";
    }
}
