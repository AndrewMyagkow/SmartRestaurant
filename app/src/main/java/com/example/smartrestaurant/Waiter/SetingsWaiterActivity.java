package com.example.smartrestaurant.Waiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Cook.SetingsCookActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SetingsWaiterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings_waiter);
        Button waiterOutBtn = findViewById(R.id.WaiterExitBtn);
        waiterOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent WaiterOutIntent = new Intent(SetingsWaiterActivity.this, MainActivity.class);
            startActivity(WaiterOutIntent);
        });
    }
}