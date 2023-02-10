package com.example.smartrestaurant.Waiter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class WaiterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        Button waiterOutBtn = findViewById(R.id.WaiterExitBtn);
        waiterOutBtn.setOnClickListener(view -> {
            Paper.book().destroy();
            Intent WaiterOutIntent = new Intent(WaiterActivity.this, MainActivity.class);
            startActivity(WaiterOutIntent);
        });
    }
}