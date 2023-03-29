package com.example.smartrestaurant.Cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.Message.Message;

import com.example.smartrestaurant.R;

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
                Intent intent = new Intent(CookActivity.this, Message.class);
                intent.putExtra("role", "Повар");
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
                Intent intent = new Intent(CookActivity.this, WriteBookCook.class);
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