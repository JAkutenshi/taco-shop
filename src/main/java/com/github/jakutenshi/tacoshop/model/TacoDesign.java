package com.github.jakutenshi.tacoshop.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TacoDesign {

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "Ingredients list shouldn't be empty")
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;

}
