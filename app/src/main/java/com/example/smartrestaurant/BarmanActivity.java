package com.example.smartrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BarmanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        Button barmanOutBtn = findViewById(R.id.BarmanExitBtn);
        barmanOutBtn.setOnClickListener((View view) -> {
            Intent BarmanOutIntent = new Intent(BarmanActivity.this,MainActivity.class);
            startActivity(BarmanOutIntent);
        });
    }
}