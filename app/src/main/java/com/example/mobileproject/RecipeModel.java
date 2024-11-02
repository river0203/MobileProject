package com.example.mobileproject;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();

    public void plusIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public void printIngredients() {
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
    }
}
