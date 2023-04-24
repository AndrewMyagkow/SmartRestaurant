package com.example.smartrestaurant.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Barman.SetingsBarmanActivity;
import com.example.smartrestaurant.Cook.SetingsCookActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        Button adminOutBtn = findViewById(R.id.AdminExitBtn);
        adminOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}