package com.example.mobileproject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseJson {

    public static List<Menu_Init> parseJsonData(String jsonData) {
        List<Menu_Init> menuList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject menuObject = jsonObject.getJSONObject(key);

                String name = menuObject.getString("name");
                JSONArray ingredientsArray = menuObject.getJSONArray("ingredients");

                List<String> ingredients = new ArrayList<>();
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }

                menuList.add(new Menu_Init(name, ingredients));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
