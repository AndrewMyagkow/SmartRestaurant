package com.example.smartrestaurant.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.Menu.MenuActivity;
import com.example.smartrestaurant.R;

public class AdminActivity extends AppCompatActivity {
    private ImageView biznes,writebook,menu,chat,reserved;
    private ImageView setings,zalobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        biznes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,BiznesActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,WriteBookActivity.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });
        reserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,ReservedActivity.class);
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,SetingsActivity.class);
                startActivity(intent);
            }
        });
        zalobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,ZalobiActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        biznes = findViewById(R.id.biznes);
        writebook = findViewById(R.id.writebook);
        menu = findViewById(R.id.menu);
        chat = findViewById(R.id.chat);
        reserved = findViewById(R.id.reserved);

        setings = findViewById(R.id.setings);
        zalobi = findViewById(R.id.zalobi);
    }
}