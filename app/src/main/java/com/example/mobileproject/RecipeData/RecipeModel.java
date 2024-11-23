package com.example.mobileproject.RecipeData;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private static List<String> jsonRecipe = new ArrayList<>();
    private static String strIngredientList;

    //  Plus_Plus_Page에서 재료이름을 받음
    public void plusIngredients(String ingredient) {
        this.ingredients.add(ingredient);
        strIngredientList = String.join(" ", ingredients);
    }

    // 재료 리스트를 보냄 -> NextActivity
    public List<String> getIngredients() {
        return this.ingredients;
    }

    // RecipeClient에 재료를 보냄
    public String getStrIngredientList() {
        return strIngredientList;
    }

    // JSON형식 데이터 리스트를 RecipeClient에서 가져오기
    public void setJsonRecipe(String recipe) {
        jsonRecipe.add(recipe);
        System.out.println(jsonRecipe);
    }

    // 리스트 변환이 두번 됨
    // JSON형식의 데이터 리스트를 Recommend_Menu_page에 전달
    public List<String> getJsonRecipe() {return jsonRecipe;
    }

    // NextActivity에서 리스트를 삭제할 때 사용
    public void removeIngredients(List<String> ingredientsToRemove) {
        this.ingredients.removeAll(ingredientsToRemove);
    }

}
