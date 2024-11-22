package com.example.mobileproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.RecipeData.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class Delet_Page extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private List<String> selectedIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // RecipeModel에서 재료 가져오기
        RecipeModel recipeModel = new RecipeModel();
        List<String> ingredients = recipeModel.getIngredients();

        // 선택된 재료 초기화
        selectedIngredients = new ArrayList<>();

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredientAdapter(ingredients, selectedIngredients);
        recyclerView.setAdapter(adapter);

        // 완료 버튼 클릭 이벤트
        Button completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(v -> {
            // RecipeModel에서 선택된 재료 삭제
            recipeModel.removeIngredients(selectedIngredients);
            System.out.println("Updated ingredients: " + recipeModel.getIngredients());
            finish(); // Activity 종료
        });
    }
}
