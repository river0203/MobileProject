package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

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

        // Intent에서 JSON 데이터 가져오기
//        Intent intent = getIntent();
//        String jsonRecipe = intent.getStringExtra("recipe_json");

        // 테스트용 하드코딩
        String jsonRecipe = "{\n" +
                "    \"steps\": {\n" +
                "        \"1\": \"양파와 닭가슴살을 적당한 크기로 잘라준다.\",\n" +
                "        \"2\": \"팬에 기름을 두르고 양파를 볶는다.\",\n" +
                "        \"3\": \"양파가 익으면 닭가슴살을 넣고 함께 볶는다.\",\n" +
                "        \"4\": \"양념장을 넣고 볶아 익힌다.\",\n" +
                "        \"5\": \"완성된 요리를 그릇에 담아 완성한다.\"\n" +
                "    }\n" +
                "}";

        // JSON 데이터 파싱
        recipeSteps = parseRecipeSteps(jsonRecipe);

        // ViewPager2 설정
        viewPager = findViewById(R.id.viewPager);
        adapter = new RecipePagerAdapter(this, recipeSteps);
        viewPager.setAdapter(adapter);
    }

    // JSON 데이터를 파싱하여 단계 리스트 반환
    private List<String> parseRecipeSteps(String jsonData) {
        List<String> steps = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject stepsObject = jsonObject.getJSONObject("steps");

            // steps 객체에서 모든 단계 내용 추가
            for (int i = 1; i <= stepsObject.length(); i++) {
                String stepKey = String.valueOf(i);
                if (stepsObject.has(stepKey)) {
                    steps.add(stepsObject.getString(stepKey));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return steps;
    }
}
