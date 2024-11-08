package com.example.mobileproject;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();

    public void setPulsIngredients(String ingredient) {
        this.ingredients.add(ingredient);
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    public void printAryList()
    {
        for(String str : ingredients)
        {
            System.out.println(str);
        }
    }
}
