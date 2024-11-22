package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sion_main);

        // RecipeModel에서 JSON 데이터 가져오기 (하드코딩된 JSON 데이터)
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

        // 다음 Activity로 이동하는 버튼
        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE); // 처음에는 숨김

        // ViewPager2 페이지 변경 리스너 추가
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 마지막 단계인지 확인
                if (position == recipeSteps.size() - 1) {
                    nextButton.setVisibility(View.VISIBLE); // 버튼 표시
                } else {
                    nextButton.setVisibility(View.GONE); // 버튼 숨김
                }
            }
        });

        // 버튼 클릭 시 다음 Activity로 이동
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(SionActivity.this, NextActivity.class);
            startActivity(intent);
        });
    }

    // JSON 데이터를 파싱하여 단계 리스트 반환
    private List<String> parseRecipeSteps(String jsonData) {
        List<String> steps = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject stepsObject = jsonObject.getJSONObject("steps");

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
