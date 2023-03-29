package com.example.smartrestaurant.Barman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.R;

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
                Intent intent = new Intent(BarmanActivity.this, Message.class);
                intent.putExtra("role", "Бармэн");
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
                Intent intent = new Intent(BarmanActivity.this, WriteBookBarman.class);
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