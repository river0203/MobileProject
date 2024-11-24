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
import com.example.mobileproject.RecipeData.RecipeClient;
import com.example.mobileproject.RecipeData.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class Plus_Main_Page extends AppCompatActivity {
    private static final String TAG = "Plus_Main_Page";

    private List<Ingredient_Item_Init> ingredientItemList = new ArrayList<>();
    private Plus_Main_Page_Adapter ingredientAdapter;
    private Button btnRecipeBtn;
    private RecipeClient recipeClient = RecipeClient.getClientInstance();
    private RecipeModel recipeModel = RecipeModel.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus_main_page);

        // 리클라이너 어댑터에서 저장 바인드한 걸 여기서 띄우기
        RecyclerView recyclerView = findViewById(R.id.ingredient_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientAdapter = new Plus_Main_Page_Adapter(this, ingredientItemList);
        recyclerView.setAdapter(ingredientAdapter);

       // 네트워크 실행,
        btnRecipeBtn = findViewById(R.id.recommend_button);
        btnRecipeBtn.setOnClickListener(v -> {
            // 네트워크 작업을 백그라운드 스레드에서 실행
            new Thread(() -> {
                 recipeClient.connectToServer();
            }).start();
        });



        // plus_plus_page로 이동하면서 결과 가져오라고 하기
        Button plus_PlusButton = findViewById(R.id.plus_plus_page);
        plus_PlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Plus_Main_Page.this, Plus_Plus_Page.class);
                startActivityForResult(intent, 100);
            }
        });

        // MainActivity로 뒤로가기
        Button back_MainActivity = findViewById(R.id.back_mainActivity_button);
        back_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(Plus_Main_Page.this, MainActivity.class );
                startActivity(backIntent);
                finish();
            }
        });
    }


    // plus_plus_page에서 반환된 데이터를 처리 후에 리스트에 추가하고 리싸클너뷰 업데이트
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            int quantity = data.getIntExtra("quantity", 0);


            ingredientItemList.add(new Ingredient_Item_Init(name, quantity));

            Log.d(TAG, "Current ingredientItemList:");
            for (Ingredient_Item_Init item : ingredientItemList) {
                Log.d(TAG, "Ingredient: " + item.getIngredint_name() + ", Quantity: " + item.getQuantity());
            }

            ingredientAdapter.notifyDataSetChanged();
        }
    }
}
