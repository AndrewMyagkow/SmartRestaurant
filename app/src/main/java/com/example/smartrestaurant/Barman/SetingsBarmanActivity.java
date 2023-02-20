package com.example.smartrestaurant.Barman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SetingsBarmanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings_barman);
        Button barmanOutBtn = findViewById(R.id.BarmanExitBtn);
        barmanOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent BarmanOutIntent = new Intent(SetingsBarmanActivity.this, MainActivity.class);
            startActivity(BarmanOutIntent);
        });
    }
}