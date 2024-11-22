package com.example.mobileproject.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class Step_Page extends AppCompatActivity {
    private ViewPager2 viewPager;
    private RecipePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sion_main);

        String menuName = getIntent().getStringExtra("menu_name");
        List<String> recipeSteps = getIntent().getStringArrayListExtra("menu_steps");

        if (recipeSteps == null || recipeSteps.isEmpty()) {
            Toast.makeText(this, "레시피 단계 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewPager = findViewById(R.id.viewPager);
        adapter = new RecipePagerAdapter(this, recipeSteps);
        viewPager.setAdapter(adapter);

        setTitle(menuName);

        // Next 버튼 초기화
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);

        // ViewPager2 페이지 변경 리스너
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 마지막 페이지인지 확인
                if (position == adapter.getItemCount() - 1) { // adapter의 마지막 인덱스와 비교
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.GONE);
                }
            }
        });

        // Next 버튼 클릭 이벤트
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Step_Page.this, Delet_Page.class);
            startActivity(intent);
            finish();
        });
    }

}
