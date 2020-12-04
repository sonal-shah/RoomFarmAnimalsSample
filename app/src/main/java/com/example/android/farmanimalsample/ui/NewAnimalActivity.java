package com.example.android.farmanimalsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.farmanimalsample.R;


public class NewAnimalActivity extends AppCompatActivity {
    private EditText newAnimal_text;
    public static final String EXTRA_REPLY = "com.example.android.farmanimalsample.REPLY";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_animal);
        Button save_btn = findViewById(R.id.save_button);
        newAnimal_text = findViewById(R.id.newanimal_edittext);
        save_btn.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (TextUtils.isEmpty(newAnimal_text.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = newAnimal_text.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


    }
}
