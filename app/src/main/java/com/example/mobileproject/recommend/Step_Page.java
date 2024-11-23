package com.example.mobileproject.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;

import java.util.List;

// AppCompateActivity를 상속받았기 때문에 엑티비티로써 이 클래스는 작동힘.
// MenuAdapter로부터 레시피 이름과 단계를 전달 받음.
public class Step_Page extends AppCompatActivity {
    // 단계를 스크롤 가능한 페이지로 보여주는 ViewPager2객체
    private ViewPager2 viewPager;
    // 이 인스턴스 객체가 Json안에 있는 요리 방법들을 여기로 보내준다.
    private RecipePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sion_main);

        // Menu_adapter가 intent를 이용해 데이터를 전달한것을 여기에서 받는다.
        String menuName = getIntent().getStringExtra("menu_name");
        List<String> recipeSteps = getIntent().getStringArrayListExtra("menu_steps");

        // 레시피에 단계가 null값이거나 비어있으면 레시피가 비었다고 알려주고 엑티비티를 끝낸다.
        if (recipeSteps == null || recipeSteps.isEmpty()) {
            Toast.makeText(this, "레시피 단계 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        viewPager = findViewById(R.id.viewPager);
        // RecipePagerAdapter클래스로부터 리시피 단계를 ViewPager2와 연결한다.
        adapter = new RecipePagerAdapter(this, recipeSteps);
        viewPager.setAdapter(adapter);

        setTitle(menuName);

        // 마지막 페이지에 넘어가기 버튼 생성
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 마지막 페이지인지 확인
                if (position == adapter.getItemCount() - 1) {
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.GONE);
                }
            }
        });

        // Delet_Page로 넘어가는 역활
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Step_Page.this, Delet_Page.class);
            startActivity(intent);
            finish();
        });
    }

}
