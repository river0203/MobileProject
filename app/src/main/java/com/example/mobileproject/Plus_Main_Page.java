package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Plus_Main_Page extends AppCompatActivity {
    private static final String TAG = "Plus_Main_Page"; // Log 태그
    private List<Ingredient_Item_Init> ingredientItemList = new ArrayList<>();
    private Ingredient_Adapter ingredientAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus_main_page);


        RecyclerView recyclerView = findViewById(R.id.ingredient_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientAdapter = new Ingredient_Adapter(this, ingredientItemList);
        recyclerView.setAdapter(ingredientAdapter);



        Button plus_PlusButton = findViewById(R.id.plus_plus_page);
        plus_PlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click"); // 버튼 클릭 확인 로그
                Intent intent = new Intent(Plus_Main_Page.this, Plus_Plus_Page.class);
                startActivityForResult(intent, 100);
            }
        });



        Button recommendButton = findViewById(R.id.recommend_button);
        recommendButton.setOnClickListener(view -> {
            Log.d(TAG, "Recommend click");
            JSONObject jsonResult = Json_Conversion.convertToJSON(ingredientItemList);

            // Logcat에서 변환된 JSON 확인 (Json_Conversion에서 이미 출력함)
            Log.d(TAG, "Final JSON Result: " + jsonResult.toString());
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Plus_Plus_Page에서 반환된 데이터 처리
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            int quantity = data.getIntExtra("quantity", 0);

            // Log로 전달받은 데이터 확인
            Log.d(TAG, "Received Data - Name: " + name + ", Quantity: " + quantity);

            // ingredientItemList에 추가
            ingredientItemList.add(new Ingredient_Item_Init(name, quantity));

            // 현재 리스트 상태를 Log로 출력
            Log.d(TAG, "Current ingredientItemList:");
            for (Ingredient_Item_Init item : ingredientItemList) {
                Log.d(TAG, "Ingredient: " + item.getIngredint_name() + ", Quantity: " + item.getQuantity());
            }

            // RecyclerView 갱신
            ingredientAdapter.notifyDataSetChanged();
        }
    }
}
