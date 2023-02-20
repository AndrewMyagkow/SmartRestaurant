package com.example.smartrestaurant.Barman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.ChatActivity;
import com.example.smartrestaurant.Admin.SetingsActivity;
import com.example.smartrestaurant.Admin.WriteBookActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class BarmanActivity extends AppCompatActivity {
    private ImageView writebook,chat;
    private ImageView setings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        init();


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, ChatBarmanActivity.class);
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, SetingsBarmanActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, WriteBookBarmanActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        writebook = findViewById(R.id.writebookbarman);
        chat = findViewById(R.id.chatbarman);
        setings = findViewById(R.id.setingsbarman);
    }
}