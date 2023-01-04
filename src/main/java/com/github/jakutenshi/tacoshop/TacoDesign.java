package com.github.jakutenshi.tacoshop;

import lombok.Data;

import java.util.List;

@Data
public class TacoDesign {
    private String name;
    private List<String> ingredients;
}
