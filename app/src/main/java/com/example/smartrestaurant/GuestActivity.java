package com.example.smartrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Button guestOutBtn = findViewById(R.id.GuestExitBtn);
        guestOutBtn.setOnClickListener((View view) -> {
            Intent GuestOutIntent = new Intent(GuestActivity.this,MainActivity.class);
            startActivity(GuestOutIntent);
        });
    }
}