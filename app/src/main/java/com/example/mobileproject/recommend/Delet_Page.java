package com.example.mobileproject.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.MainActivity;
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

        // 하드코딩된 재료 리스트
        List<String> ingredients = new ArrayList<>();
        ingredients.add("egg");
        ingredients.add("ham");

        // 리클라이너 뷰에 표시
        selectedIngredients = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Delet_Page_Adapter(ingredients, selectedIngredients);
        recyclerView.setAdapter(adapter);

        // 선택된 재료를 삭제 버튼 동작으로 처리
        Button completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(v -> {
            // 선택된 재료 출력
            System.out.println("선택된 재료: " + selectedIngredients);

            // 리스트에서 선택된 재료 제거
            ingredients.removeAll(selectedIngredients);
            adapter.notifyDataSetChanged();

            // MainActivity로 돌아가기
            Intent intent = new Intent(Delet_Page.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}