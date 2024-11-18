package com.example.mobileproject.recommend;

import java.util.List;

public class Menu_Init {
    private String name;
    private List<String> ingredients;

    public Menu_Init(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
