package com.example.shoppingcart.controller;

import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartEntity cartEntity;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("numberOfItems", cartEntity.count());
        return "product/list";
    }

    @GetMapping("create")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("create")
    public String create(@ModelAttribute Product product, RedirectAttributes redirect) {
        productService.create(product);
        redirect.addFlashAttribute("create", "Save product successfully!");
        return "redirect:/products";
    }
}
