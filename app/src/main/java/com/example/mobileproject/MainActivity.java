package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.RecipeData.RecipeClient;
import com.example.mobileproject.RecipeData.RecipeModel;
import com.example.mobileproject.plus.Plus_Main_Page;
import com.example.mobileproject.recommend.Recommend_Menu_Page;

public class MainActivity extends AppCompatActivity {

    private RecipeClient clientInstance = RecipeClient.getClientInstance();
    private static RecipeModel recipeModel = RecipeModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button plusButton = findViewById(R.id.plus_page);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Plus_Main_Page.class);
                startActivity(intent);
            }
        });

        Button recommendButton = findViewById(R.id.recommend_button);
        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(recipeModel.getIngredients().isEmpty()) {
                    Toast.makeText(MainActivity.this, "재료를 입력해주세요", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, Plus_Main_Page.class);
                    startActivity(intent);
                }

                try {
                    Intent intent = new Intent(MainActivity.this, Recommend_Menu_Page.class);
                    startActivity(intent);

                    new Thread(() -> {
                        try {
                            clientInstance.connectToServer();
                        } catch (Exception e) {
                            Log.e("MainActivity", "Server Error", e);
                        }
                    }).start();

                } catch (Exception e) {
                    Log.e("MainActivity", "Error", e);
                    Toast.makeText(MainActivity.this, "페이지 이동 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
