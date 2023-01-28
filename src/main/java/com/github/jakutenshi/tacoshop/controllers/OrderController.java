package com.github.jakutenshi.tacoshop.controllers;

import com.github.jakutenshi.tacoshop.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final static String ORDER_FORM_VIEW_NAME = "orderForm";

    @GetMapping("current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return ORDER_FORM_VIEW_NAME;
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ORDER_FORM_VIEW_NAME;
        }
        // ToDo: сохраняем заказ в БД
        log.info("Processing order: " + order);
        return "redirect:/";
    }

}
