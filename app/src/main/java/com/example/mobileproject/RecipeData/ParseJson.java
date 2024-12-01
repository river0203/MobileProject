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
            // JSON 데이터의 최상위 객체 생성
            JSONObject jsonObject = new JSONObject(jsonData);

            // "recipes" 배열 가져오기
            JSONArray recipesArray = jsonObject.getJSONArray("recipes");

            // 배열 순회
            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipeObject = recipesArray.getJSONObject(i);

                // 메뉴 이름 파싱
                String name = recipeObject.optString("recipe_name", "이름 없음");

                // 단계 파싱
                List<String> steps = new ArrayList<>();
                if (recipeObject.has("steps") && !recipeObject.isNull("steps")) {
                    JSONArray stepsArray = recipeObject.getJSONArray("steps");
                    for (int j = 0; j < stepsArray.length(); j++) {
                        steps.add(stepsArray.getString(j));
                    }
                }

                // Menu_Init 객체 생성
                menuList.add(new Menu_Init(name, steps));
            }
        } catch (Exception e) {
            System.out.println("JSON 파싱 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return menuList;
    }

}
