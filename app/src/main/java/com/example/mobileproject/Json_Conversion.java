package com.example.mobileproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Json_Conversion {
    private static final String TAG = "Json_Conversion";

    public static JSONObject convertToJSON(List<Ingredient_Item_Init> ingredientList) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            // ingredientList의 데이터를 JSON 배열로 변환
            for (Ingredient_Item_Init item : ingredientList) {
                JSONObject ingredientObject = new JSONObject();
                ingredientObject.put("name", item.getIngredint_name());
                ingredientObject.put("quantity", item.getQuantity());
                jsonArray.put(ingredientObject);
            }

            // JSON 객체에 배열 추가
            jsonObject.put("ingredients", jsonArray);
            Log.d(TAG, "Converted JSON: " + jsonObject.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error converting to JSON", e);
        }

        return jsonObject;
    }
}
