package com.example.mobileproject;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private static List<String> jsonRecipe = new ArrayList<>();
    private static String strIngredientList;

    public void plusIngredients(String ingredient) {
        this.ingredients.add(ingredient);
        strIngredientList = String.join(" ", ingredients);
    }
    public List<String> getIngredients() {
        return this.ingredients;
    }
    public String getStrIngredientList() {
        return strIngredientList;
    }

    public void setJsonRecipe(String recipe) {
        jsonRecipe.add(recipe);
        System.out.println(jsonRecipe);
    }
    public List<String> getJsonRecipe() {
        return jsonRecipe;
    }

}
