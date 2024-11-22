package com.example.mobileproject.plus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;


public class Plus_Plus_Page extends AppCompatActivity {
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus_plus_page);

        EditText nameInput = findViewById(R.id.name_input);
        EditText quantityInput = findViewById(R.id.editTextNumber);
        Button minusButton = findViewById(R.id.button_minus);
        Button plusButton = findViewById(R.id.button_plus);
        Button saveButton = findViewById(R.id.save_ingredient);


        quantityInput.setText(String.valueOf(quantity));


        minusButton.setOnClickListener(view -> {
            if (quantity > 0) {
                quantity--;
                quantityInput.setText(String.valueOf(quantity));
            } else {
                Toast.makeText(Plus_Plus_Page.this, "0보다 큰 값을 입력해 주세요", Toast.LENGTH_SHORT).show();
            }
        });

        plusButton.setOnClickListener(view -> {
            quantity++;
            quantityInput.setText(String.valueOf(quantity));
        });


        saveButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();

            if (name.isEmpty() || quantity <= 0) {
                Toast.makeText(Plus_Plus_Page.this, "이름과 수량을 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("quantity", quantity);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
