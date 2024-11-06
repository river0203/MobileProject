package com.example.mobileproject;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClient = findViewById(R.id.button);

        btnClient.setOnClickListener(v -> {
            // 네트워크 작업을 백그라운드 스레드에서 실행
            new Thread(() -> {
                RecipeClient client = new RecipeClient();
                client.connectToServer();
            }).start();
        });
    }
}
