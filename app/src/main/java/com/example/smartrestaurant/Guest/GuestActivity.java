package com.example.smartrestaurant.Guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ZalobiBook.ZalobiBookActivity;

public class GuestActivity extends AppCompatActivity {
    private ImageView salaty,hotbluda,syp,deserty,zakyski,garniry,fastfud,napitki,vinnaakarta,zalobi,pokypk,settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        pokypk = findViewById(R.id.pokypk);
        pokypk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this,Basket.class);
                startActivity(intent);
            }
        });
        salaty = findViewById(R.id.salat);
        salaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Салаты");
                startActivity(intent);
            }
        });
        hotbluda = findViewById(R.id.hotbluda);
        hotbluda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Горячие блюда");
                startActivity(intent);
            }
        });
        syp = findViewById(R.id.syp);
        syp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Супы");
                startActivity(intent);
            }
        });
        deserty = findViewById(R.id.desert);
        deserty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Десерты");
                startActivity(intent);
            }
        });
        zakyski = findViewById(R.id.holodnzakyski);
        zakyski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Закуски");
                startActivity(intent);
            }
        });
        garniry = findViewById(R.id.garniry);
        garniry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Гарниры");
                startActivity(intent);
            }
        });
        fastfud = findViewById(R.id.fastfyd);
        fastfud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "ФастФуд");
                startActivity(intent);
            }
        });
        napitki = findViewById(R.id.napitki);
        napitki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Напитки");
                startActivity(intent);
            }
        });
        vinnaakarta = findViewById(R.id.vinnyakarta);
        vinnaakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, Meny.class);
                intent.putExtra("category", "Винная карта");
                startActivity(intent);
            }
        });
        zalobi = findViewById(R.id.zalobiguest);
        zalobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, GuestZalobiBook.class);
                startActivity(intent);
            }
        });
        settings = findViewById(R.id.settingguest);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestActivity.this, SettingsGuest.class);
                startActivity(intent);
            }
        });
    }
}