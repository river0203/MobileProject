package com.example.mobileproject.plus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.MainActivity;
import com.example.mobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class Plus_Main_Page extends AppCompatActivity {
    private static final String TAG = "Plus_Main_Page";
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
                Intent intent = new Intent(Plus_Main_Page.this, Plus_Plus_Page.class);
                startActivityForResult(intent, 100);
            }
        });


        Button back_MainActivity = findViewById(R.id.back_mainActivity_button);
        back_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(Plus_Main_Page.this, MainActivity.class );
                startActivity(backIntent);
                finish();
            }
        });





        Button recommendButton = findViewById(R.id.recommend_button);
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
