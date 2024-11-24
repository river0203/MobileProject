package com.example.mobileproject.recommend;

import java.util.List;

public class Menu_Init {
    private String name;
//    private List<String> ingredients;
    private List<String> steps;

    public Menu_Init(String name, List<String> steps) {
        this.name = name;
//        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

//    public List<String> getIngredients() {
//        return ingredients;
//    }

    public List<String> getSteps() {
        return steps;
    }
}
