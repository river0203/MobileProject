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

import java.util.List;

public class Recommend_Menu_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_menu_page);

        Button backMainActivity = findViewById(R.id.back_mainActivity_button);
        backMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(Recommend_Menu_Page.this, MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Intent에서 서버 데이터를 가져오기
        String jsonData = getIntent().getStringExtra("server_response");

        if (jsonData != null && !jsonData.isEmpty()) {
            List<Menu_Init> menuList = ParseJson.parseJsonData(jsonData);
            Menu_Adapter adapter = new Menu_Adapter(menuList);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "서버 데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

        // ParseJson 클래스 사용
        List<Menu_Init> menuList = ParseJson.parseJsonData(jsonData);

        Menu_Adapter adapter = new Menu_Adapter(menuList);
        recyclerView.setAdapter(adapter);
    }
}
