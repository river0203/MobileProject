package com.example.mobileproject.RecipeData;
import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private List<String> ingredients = new ArrayList<>();
    private static String jsonRecipe;
    private static String strIngredientList;

    // 여러 액티비에서 동일한 인스턴스를 사용하기 위한 싱글톤 패턴.
    private static RecipeModel instance;
    private RecipeModel() {}
    public static RecipeModel getInstance() {
        if (instance == null) {
            instance = new RecipeModel();
        }
        return instance;
    }
    // 여기까지가 싱글톤 패턴

    //  Plus_Plus_Page에서 재료이름을 받음
    public void plusIngredients(String ingredient) {
        this.ingredients.add(ingredient);
        strIngredientList = String.join(" ", ingredients);
        System.out.println(strIngredientList);
    }

    // 재료 리스트를 보냄 -> NextActivity
    public List<String> getIngredients() {
        return this.ingredients;
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("ham");
//        strings.add("eggs");
//        strings.add("rice");
//        return strings;
    }

    // RecipeClient에 재료를 보냄
    public String getStrIngredientList() {
        return strIngredientList;
    }

    // JSON형식 데이터 리스트를 RecipeClient에서 가져오기
    public void setJsonRecipe(String recipe) {
        jsonRecipe = recipe;
        System.out.println(jsonRecipe);
    }

    // 리스트 변환이 두번 됨
    // JSON형식의 데이터 리스트를 Recommend_Menu_page에 전달
    public String getJsonRecipe() {

          return jsonRecipe;
    }

    // NextActivity에서 리스트를 삭제할 때 사용
    public void removeIngredients(List<String> ingredientsToRemove) {
        this.ingredients.removeAll(ingredientsToRemove);
    }
}
