package com.example.smartrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WaiterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        Button waiterOutBtn = findViewById(R.id.WaiterExitBtn);
        waiterOutBtn.setOnClickListener(view -> {
            Intent WaiterOutIntent = new Intent(WaiterActivity.this,MainActivity.class);
            startActivity(WaiterOutIntent);
        });
    }
}