package com.example.smartrestaurant.Cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Barman.ChatBarmanActivity;
import com.example.smartrestaurant.Barman.SetingsBarmanActivity;
import com.example.smartrestaurant.Barman.WriteBookBarmanActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class CookActivity extends AppCompatActivity {
    private ImageView writebook,chat;
    private ImageView setings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        init();


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, ChatCookActivity.class);
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, SetingsCookActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, WriteBookCookActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        writebook = findViewById(R.id.writebookcook);
        chat = findViewById(R.id.chatcook);
        setings = findViewById(R.id.setingscook);
    }
}