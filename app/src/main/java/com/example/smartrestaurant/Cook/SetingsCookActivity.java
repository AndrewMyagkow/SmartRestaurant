package com.example.smartrestaurant.Cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Barman.SetingsBarmanActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SetingsCookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_cook);
        Button barmanOutBtn = findViewById(R.id.CookExitBtn);
        barmanOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent BarmanOutIntent = new Intent(SetingsCookActivity.this, MainActivity.class);
            startActivity(BarmanOutIntent);
        });
    }
}