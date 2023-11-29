package com.example.shoppingcart.controller;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.entity.CartEntity;
import com.example.shoppingcart.entity.OrderDetailsEntity;
import com.example.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

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

    @PostMapping("/change-quantity")
    @ResponseBody
    public ResponseEntity<?> changeQuantity(@RequestParam String action, @RequestParam Long id) {
        Map<String, Object> response = new HashMap<>();
        Product product = new Product();
        product.setId(id);

        switch (action) {
            case "increment" -> cartService.addItem(product);
            case "decrement" -> cartService.removeItem(product);
            default -> {
                response.put("message", "method has not been defined");
                return ResponseEntity.ok(response);
            }
        }
        int quantity = Optional.of(cartEntity.getAll())
                .orElse(new ArrayList<>())
                .stream()
                .filter(details -> Objects.equals(details.getProductEntity().getId(), product.getId()))
                .findFirst()
                .orElse(new OrderDetailsEntity())
                .getQuantity();

        response.put("numberOfItems", cartEntity.count());
        response.put("quantity", quantity);
        response.put("message", "success");

        return ResponseEntity.ok(response);
    }
}
