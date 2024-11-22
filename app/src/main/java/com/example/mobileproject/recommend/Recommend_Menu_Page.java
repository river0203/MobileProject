package com.example.mobileproject.recommend;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.RecipeData.RecipeModel;

import java.util.List;

public class Recommend_Menu_Page extends AppCompatActivity {
    private RecipeModel recipeModel = new RecipeModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_menu_page);

        // RecyclerView 설정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // RecipeModel에서 JSON 데이터 가져오기
        List<String> jsonRecipeList = recipeModel.getJsonRecipe();
        if (jsonRecipeList.isEmpty()) {
            Toast.makeText(this, "서버와 연결이 안됨", Toast.LENGTH_SHORT).show();
            return;
        }

        // JSON 데이터를 합쳐서 하나의 문자열로 처리
        StringBuilder jsonDataBuilder = new StringBuilder();
        for (String json : jsonRecipeList) {
            jsonDataBuilder.append(json);
        }
        String jsonData = jsonDataBuilder.toString();

        // JSON 데이터 파싱
        if (jsonData != null && !jsonData.isEmpty()) {
            List<Menu_Init> menuList = ParseJson.parseJsonData(jsonData);

            // RecyclerView에 데이터 설정
            Menu_Adapter adapter = new Menu_Adapter(menuList);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "JSON 데이터가 비어있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
