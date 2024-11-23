package com.example.mobileproject.recommend;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.RecipeData.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class Delet_Page extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Delet_Page_Adapter adapter;
    private List<String> selectedIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // RecipeModel에서 재료 가져오기
        RecipeModel recipeModel = new RecipeModel();
        List<String> ingredients = recipeModel.getIngredients();

        // 리클라이너 뷰에 표시
        selectedIngredients = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Delet_Page_Adapter(ingredients, selectedIngredients);
        recyclerView.setAdapter(adapter);


        // RecipeModel에서 선택된 재료 삭제
        Button completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(v -> {

            recipeModel.removeIngredients(selectedIngredients);
            System.out.println("Updated ingredients: " + recipeModel.getIngredients());
            finish();
        });
    }
}
