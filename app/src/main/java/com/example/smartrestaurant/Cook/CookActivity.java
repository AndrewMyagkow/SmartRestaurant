package com.example.smartrestaurant.Cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class CookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        Button cookOutBtn = findViewById(R.id.CookExitBtn);
        cookOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent CookOutIntent = new Intent(CookActivity.this, MainActivity.class);
            startActivity(CookOutIntent);
        });
    }
}