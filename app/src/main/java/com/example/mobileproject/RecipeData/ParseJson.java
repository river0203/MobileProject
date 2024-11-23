package com.example.mobileproject.RecipeData;

import com.example.mobileproject.recommend.Menu_Init;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    public static List<Menu_Init> parseJsonData(String jsonData) {
        List<Menu_Init> menuList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray recipesArray = jsonObject.getJSONArray("recipes");

            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipeObject = recipesArray.getJSONObject(i);

                // 메뉴 이름 파싱
                String name = recipeObject.getString("name");

                // 재료 파싱
                JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");
                List<String> ingredients = new ArrayList<>();
                for (int j = 0; j < ingredientsArray.length(); j++) {
                    ingredients.add(ingredientsArray.getString(j));
                }

                // 단계 파싱
                JSONObject stepsObject = recipeObject.getJSONObject("steps");
                List<String> steps = new ArrayList<>();
                for (int k = 1; k <= stepsObject.length(); k++) {
                    steps.add(stepsObject.getString(String.valueOf(k)));
                }

                // Menu_Init 객체 생성 및 리스트에 추가
                menuList.add(new Menu_Init(name, ingredients, steps));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
