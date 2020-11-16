package com.smu.team_andeu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button testExer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testExer = findViewById(R.id.exer_test_button);
        testExer.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ExerActivity.class);
            startActivity(intent);
        });
    }
}