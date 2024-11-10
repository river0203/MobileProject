package com.example.mobileproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
//TODO : arraylist 단어 합치기, 단어 합쳐서 저장 -> 서버 전달
public class MainActivity extends AppCompatActivity {
    String text;

    EditText editText;
    TextView inputIngredient;
    Button btnIngredientSave;
    Button btnClient;

    RecipeModel ingredientList = new RecipeModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        inputIngredient = findViewById(R.id.inputIngredient);
        btnIngredientSave = findViewById(R.id.button2);
        btnClient = findViewById(R.id.button);

        btnClient.setOnClickListener(v -> {
            // 네트워크 작업을 백그라운드 스레드에서 실행
            new Thread(() -> {
                RecipeClient client = new RecipeClient();
                client.connectToServer();
            }).start();
        });

        btnIngredientSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = editText.getText().toString();
                ingredientList.setPulsIngredients(text);
                ingredientList.printAryList();

                if(text != null)
                    inputIngredient.setText(text);
                editText.setText("");
            }
        });
    }
}
