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

            // 메뉴 이름 파싱
            String name = jsonObject.optString("recipe_name", "이름 없음");

            // 단계 파싱
            List<String> steps = new ArrayList<>();
            if (jsonObject.has("steps") && !jsonObject.isNull("steps")) {
                JSONArray stepsArray = jsonObject.getJSONArray("steps");
                for (int i = 0; i < stepsArray.length(); i++) {
                    steps.add(stepsArray.getString(i));
                }
            } else {
                System.out.println("'steps' 키가 없거나 null입니다.");
            }

            // Menu_Init 객체 생성 (재료 제거)
            menuList.add(new Menu_Init(name, steps));

        } catch (Exception e) {
            System.out.println("JSON 파싱 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return menuList;
    }
}
