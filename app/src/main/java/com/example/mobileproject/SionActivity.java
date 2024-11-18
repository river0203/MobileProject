package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SionActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private RecipePagerAdapter adapter;
    private List<String> recipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sion_main);

        // Intent에서 데이터 가져오기
        Intent intent = getIntent();
        String menuName = intent.getStringExtra("menu_name");
        String menuIngredients = intent.getStringExtra("menu_ingredients");

        // 가져온 데이터를 로그로 확인하거나 UI에 표시
        System.out.println("Menu Name: " + menuName);
        System.out.println("Ingredients: " + menuIngredients);

        // JSON 데이터 (이 예제에서는 하드코딩. 실제로는 API 호출로 가져올 수 있음)
        String jsonData = "{ \"recipeName\": \"Pasta\", \"steps\": [\"Boil water in a pot.\", \"Add pasta and cook for 10 minutes.\", \"Drain water and add sauce.\", \"Mix well and serve.\"] }";

        recipeSteps = parseRecipeSteps(jsonData);

        // ViewPager2 설정
        viewPager = findViewById(R.id.viewPager);
        adapter = new RecipePagerAdapter(this, recipeSteps);
        viewPager.setAdapter(adapter);

    }

    // JSON 데이터를 파싱하여 단계 리스트 반환
    private List<String> parseRecipeSteps (String jsonData){
        List<String> steps = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray stepsArray = jsonObject.getJSONArray("steps");
            for (int i = 0; i < stepsArray.length(); i++) {
                steps.add(stepsArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return steps;
    }
}