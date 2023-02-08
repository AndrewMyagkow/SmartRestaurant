package com.example.smartrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button adminOutBtn = findViewById(R.id.AdminExitBtn);
        adminOutBtn.setOnClickListener((View view) -> {
            Intent AdminOutIntent = new Intent(AdminActivity.this,MainActivity.class);
            startActivity(AdminOutIntent);
        });
    }
}