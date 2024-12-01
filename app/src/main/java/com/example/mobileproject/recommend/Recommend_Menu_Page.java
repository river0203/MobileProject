package com.example.mobileproject.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.MainActivity;
import com.example.mobileproject.R;
import com.example.mobileproject.RecipeData.ParseJson;
import com.example.mobileproject.RecipeData.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class Recommend_Menu_Page extends AppCompatActivity {
    private RecipeModel recipeModel = RecipeModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_menu_page);

        // 메인화면 뒤로가기 버튼 추가
        Button backMainActivity = findViewById(R.id.back_mainActivity_button);
        backMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(Recommend_Menu_Page.this, MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        // 리클라너뷰 설정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));



        //서버를 사용시
        // RecipeModel에서 JSON 데이터 가져오기
        String jsonRecipeList = recipeModel.getJsonRecipe();
        if (jsonRecipeList.isEmpty()) {
            Toast.makeText(this, "서버 데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // JSON 데이터를 파싱하기 위해서 ParseJson으로 보내기
        if (jsonRecipeList != null && !jsonRecipeList.isEmpty()) {
            List<Menu_Init> menuList = ParseJson.parseJsonData(jsonRecipeList);

            // 리클라이너뷰 업데이트
            Menu_Adapter adapter = new Menu_Adapter(menuList);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "JSON 데이터가 비어있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
