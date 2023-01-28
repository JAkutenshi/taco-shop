package com.github.jakutenshi.tacoshop.controllers;

import com.github.jakutenshi.tacoshop.model.Ingredient;
import com.github.jakutenshi.tacoshop.model.TacoDesign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;

import static com.github.jakutenshi.tacoshop.model.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final static String TACO_DESIGN_VIEW_NAME = "design";
    private final static String  RESULT_TACO_DESIGN_ATTR_NAME = "resultTacoDesign";

    @GetMapping
    public String showDesignForm(Model model) {
        addIngredientsToModel(model);
        // Добавляем объект, в котором будет результат формы из View
        model.addAttribute(RESULT_TACO_DESIGN_ATTR_NAME, new TacoDesign());
        // Возвращаем пользователю View с именем "design", заполнив предварительно объект model
        return TACO_DESIGN_VIEW_NAME;
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute(RESULT_TACO_DESIGN_ATTR_NAME) TacoDesign resultTacoDesign,
                                Errors errors,
                                Model model) {
        if (errors.hasErrors()) {
            log.error("Wrong smth");
            addIngredientsToModel(model);
            return TACO_DESIGN_VIEW_NAME;
        }
        // ToDo: Здесь будет сохранение рецепта в БД
        log.info("Processing the design: " + resultTacoDesign);
        return "redirect:/orders/current";
    }

    private static void addIngredientsToModel(Model model) {
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
    }

}
