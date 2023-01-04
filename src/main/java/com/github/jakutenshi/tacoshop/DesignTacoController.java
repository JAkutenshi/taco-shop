package com.github.jakutenshi.tacoshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

import static com.github.jakutenshi.tacoshop.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        final var RESULT_TACO_DESIGN_ATTR_NAME = "resultTacoDesign";
        // Создаём каждый раз список ингредиентов
        final var ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        // Добавляем в модель ингредиенты, распределив их по типу
        Arrays.stream(Type.values()).forEach(type -> model.addAttribute(
                type.name().toLowerCase(),
                ingredients.stream()
                        .filter(ingredient -> ingredient.getType() == type)
                        .toList()));
        // Добавляем объект, в котором будет результат формы из View
        model.addAttribute(RESULT_TACO_DESIGN_ATTR_NAME, new TacoDesign());
        // Возвращаем пользователю View с именем "design", заполнив предварительно объект model
        return "design";
    }

    @PostMapping
    public String processDesign(TacoDesign design) {
        // ToDo: Здесь будет сохранение рецепта в БД
        log.info("Processing the design: " + design);
        return "redirect:/orders/current";
    }

}
