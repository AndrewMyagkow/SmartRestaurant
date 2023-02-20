package com.example.smartrestaurant.Waiter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Menu.MenuActivity;
import com.example.smartrestaurant.Cook.ChatCookActivity;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Cook.SetingsCookActivity;
import com.example.smartrestaurant.Cook.WriteBookCookActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

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
                Intent intent = new Intent(WaiterActivity.this, ChatWaiterActivity.class);
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
                Intent intent = new Intent(WaiterActivity.this, WriteBookWaiterActivity.class);
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