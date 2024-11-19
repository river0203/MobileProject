package com.example.mobileproject;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private static List<String> jsonRecipe = new ArrayList<>();
    private static String strIngredientList;

    public void setPulsIngredients(String ingredient) {
        this.ingredients.add(ingredient);
        strIngredientList = String.join(" ", ingredients);
    }
    public List<String> getIngredients() {
        return this.ingredients;
    }
    public String getStrIngredientList() {
        return strIngredientList;
    }

    public static void setJsonRecipe(String recipe) {
        jsonRecipe.add(recipe);
    }
    public static List<String> getJsonRecipe() {
        return jsonRecipe;
    }

}
