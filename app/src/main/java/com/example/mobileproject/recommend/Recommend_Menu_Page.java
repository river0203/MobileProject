package com.example.mobileproject.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        //하드 코딩
        String jsonData = "{\n" +
                "    \"recipe1\": {\n" +
                "        \"name\": \"햄 계란 볶음밥\",\n" +
                "        \"ingredients\": [\"밥\", \"햄\", \"계란\", \"양파\", \"간장\", \"식용유\", \"소금\", \"후추\"],\n" +
                "        \"steps\": [\n" +
                "            \"팬에 식용유를 두르고 다진 양파를 볶는다.\",\n" +
                "            \"양파가 투명해지면 햄을 넣고 볶는다.\",\n" +
                "            \"계란을 푼 후 볶아준다.\",\n" +
                "            \"밥을 넣고 함께 볶는다.\",\n" +
                "            \"간장, 소금, 후추로 간을 맞춘 후 완성한다.\"\n" +
                "        ]\n" +
                "    },\n" +
                "    \"recipe2\": {\n" +
                "        \"name\": \"햄 계란 김치볶음\",\n" +
                "        \"ingredients\": [\"햄\", \"계란\", \"양파\", \"김치\", \"고추장\", \"식용유\", \"소금\", \"설탕\"],\n" +
                "        \"steps\": [\n" +
                "            \"김치와 양파를 잘게 다진다.\",\n" +
                "            \"팬에 식용유를 두르고 다진 양파를 볶는다.\",\n" +
                "            \"양파가 투명해지면 햄을 넣고 볶는다.\",\n" +
                "            \"계란을 푼 후 넣고 볶는다.\",\n" +
                "            \"김치를 넣고 함께 볶아준다.\",\n" +
                "            \"고추장, 소금, 설탕으로 간을 맞추고 완성한다.\"\n" +
                "        ]\n" +
                "    },\n" +
                "    \"recipe3\": {\n" +
                "        \"name\": \"햄 계란 양파볶음\",\n" +
                "        \"ingredients\": [\"햄\", \"계란\", \"양파\", \"간장\", \"식용유\", \"설탕\", \"마늘\", \"후추\"],\n" +
                "        \"steps\": [\n" +
                "            \"팬에 식용유를 두르고 다진 마늘을 볶는다.\",\n" +
                "            \"다진 양파를 넣고 볶는다.\",\n" +
                "            \"양파가 익으면 햄을 넣고 볶는다.\",\n" +
                "            \"계란을 푼 후 넣고 볶는다.\",\n" +
                "            \"간장, 설탕, 후추로 간을 맞추고 완성한다.\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        // ParseJson 클래스 사용
        List<Menu_Init> menuList = ParseJson.parseJsonData(jsonData);

        Menu_Adapter adapter = new Menu_Adapter(menuList);
        recyclerView.setAdapter(adapter);
    }
}
