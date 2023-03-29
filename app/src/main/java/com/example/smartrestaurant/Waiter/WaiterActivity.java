package com.example.smartrestaurant.Waiter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.R;

public class WaiterActivity extends AppCompatActivity {
    private ImageView writebook,menu,chat;
    private ImageView setings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        init();


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, Message.class);
                intent.putExtra("role", "Оффициант");
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, SetingsWaiterActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, WriteBookWaiter.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, MenuWaiterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        writebook = findViewById(R.id.writebookwaiter);
        chat = findViewById(R.id.chatwaiter);
        setings = findViewById(R.id.setingswaiter);
        menu = findViewById(R.id.menuwaiter);
    }
}