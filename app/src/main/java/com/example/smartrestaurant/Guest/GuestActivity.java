package com.example.smartrestaurant.Guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class GuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Button guestOutBtn = findViewById(R.id.GuestExitBtn);
        guestOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent GuestOutIntent = new Intent(GuestActivity.this, MainActivity.class);
            startActivity(GuestOutIntent);
        });
    }
}