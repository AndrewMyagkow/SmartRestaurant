package com.example.smartrestaurant.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.Entry.RegisterActivity;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class AdminActivity extends AppCompatActivity {
    private ImageView menu,biznes,reserved,writebook,chat,zalobi,setings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button adminOutBtn = findViewById(R.id.AdminExitBtn);
        adminOutBtn.setOnClickListener((View view) -> {
            Paper.book().destroy();
            Intent AdminOutIntent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(AdminOutIntent);
        });

        init();
        menu.setOnClickListener((View view) -> {
           // Intent AdminMenu = new Intent(AdminActivity.this, MenuScrollingActivity.class );
           // AdminMenu.putExtra("hello",10);
          //  startActivity(AdminMenu);
        });
    }
    private void init()
    {
        menu = findViewById(R.id.menu);
        biznes = findViewById(R.id.biznes);
        reserved = findViewById(R.id.reserved);
        writebook = findViewById(R.id.writingBook);
        chat = findViewById(R.id.chat);
        zalobi = findViewById(R.id.zalobi);
        setings = findViewById(R.id.setings);


    }
}