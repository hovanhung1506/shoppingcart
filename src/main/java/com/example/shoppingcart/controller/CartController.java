package com.example.shoppingcart.controller;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartEntity cartEntity;

    @GetMapping
    public String list(Model model) {
        List<Cart> carts = cartService.getAll();
        model.addAttribute("carts", carts);
        model.addAttribute("numberOfItems", cartEntity.count());
        return "cart/list";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Product product) {
        cartService.addItem(product);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@ModelAttribute Product product) {
        cartService.removeItem(product);
        return "redirect:/cart";
    }
}
