package com.example.mobileproject.recommend;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // 리클라너뷰 설정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        // 하드코딩 용 테스트
        /*List<String> jsonRecipeList = new ArrayList<>();
        String jsonString = "{\n" +
                "    \"recipes\": [\n" +
                "        {\n" +
                "            \"name\": \"계란말이\",\n" +
                "            \"ingredients\": [\"계란\", \"양파\", \"햄\"],\n" +
                "            \"steps\": {\n" +
                "                \"1\": \"계란을 풀어 그릇에 담는다.\",\n" +
                "                \"2\": \"양파와 햄을 잘게 다져준다.\",\n" +
                "                \"3\": \"팬에 기름을 두르고 양파와 햄을 볶는다.\",\n" +
                "                \"4\": \"양파와 햄이 익으면 계란을 부어 주기적으로 저어가며 익힌다.\",\n" +
                "                \"5\": \"계란이 익으면 불을 끄고 그릇에 담아 완성한다.\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"양파 닭가슴살 볶음\",\n" +
                "            \"ingredients\": [\"양파\", \"닭가슴살\"],\n" +
                "            \"steps\": {\n" +
                "                \"1\": \"양파와 닭가슴살을 적당한 크기로 잘라준다.\",\n" +
                "                \"2\": \"팬에 기름을 두르고 양파를 볶는다.\",\n" +
                "                \"3\": \"양파가 익으면 닭가슴살을 넣고 함께 볶는다.\",\n" +
                "                \"4\": \"양념장을 넣고 볶아 익힌다.\",\n" +
                "                \"5\": \"완성된 요리를 그릇에 담아 완성한다.\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"양파 계란볶음밥\",\n" +
                "            \"ingredients\": [\"양파\", \"계란\", \"닭가슴살\"],\n" +
                "            \"steps\": {\n" +
                "                \"1\": \"양파와 닭가슴살을 적당한 크기로 잘라준다.\",\n" +
                "                \"2\": \"팬에 기름을 두르고 양파를 볶는다.\",\n" +
                "                \"3\": \"양파가 익으면 닭가슴살을 넣고 함께 볶는다.\",\n" +
                "                \"4\": \"계란을 넣고 볶아 익힌다.\",\n" +
                "                \"5\": \"밥을 넣고 볶아 완성한다.\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        jsonRecipeList.add(jsonString);*/



        // RecipeModel에서 JSON 데이터 가져오기
        String jsonRecipeList = recipeModel.getJsonRecipe();
        if (jsonRecipeList.isEmpty()) {
            Toast.makeText(this, "서버 데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // JSON 데이터를 합쳐서 하나의 문자열로 처리
        /*StringBuilder jsonDataBuilder = new StringBuilder();
        for (String json : jsonRecipeList) {
            jsonDataBuilder.append(json);
        }
        String jsonData = jsonDataBuilder.toString();

         */

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
