package com.example.smartrestaurant.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ZalobiBook.AddZalobiBook;
import com.example.smartrestaurant.ZalobiBook.ZalobiBookActivity;

public class AdminActivity extends AppCompatActivity {
    private String name;
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
                Intent intent = new Intent(AdminActivity.this, BiznesActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, WriteBookActivity.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, Message.class);
                intent.putExtra("role", "Администратор");
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
                Intent intent = new Intent(AdminActivity.this, ZalobiBookActivity.class);
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