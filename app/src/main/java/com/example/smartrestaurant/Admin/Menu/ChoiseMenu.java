package com.example.smartrestaurant.Admin.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Guest.Basket;
import com.example.smartrestaurant.Guest.GuestActivity;
import com.example.smartrestaurant.Guest.GuestZalobiBook;
import com.example.smartrestaurant.Guest.Meny;
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.R;

public class ChoiseMenu extends AppCompatActivity {
    private ImageView salaty,hotbluda,syp,deserty,zakyski,garniry,fastfud,napitki,vinnaakarta,back,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_menu);

        back = findViewById(R.id.back_choise_menu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        add = findViewById(R.id.add_food_admin);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
        salaty = findViewById(R.id.salat_admin);
        salaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Салаты");
                startActivity(intent);
            }
        });
        hotbluda = findViewById(R.id.hotbluda_admin);
        hotbluda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Горячие блюда");
                startActivity(intent);
            }
        });
        syp = findViewById(R.id.syp_admin);
        syp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Супы");
                startActivity(intent);
            }
        });
        deserty = findViewById(R.id.desert_admin);
        deserty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Десерты");
                startActivity(intent);
            }
        });
        zakyski = findViewById(R.id.holodnzakyski_admin);
        zakyski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Закуски");
                startActivity(intent);
            }
        });
        garniry = findViewById(R.id.garniry_admin);
        garniry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Гарниры");
                startActivity(intent);
            }
        });
        fastfud = findViewById(R.id.fastfyd_admin);
        fastfud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "ФастФуд");
                startActivity(intent);
            }
        });
        napitki = findViewById(R.id.napitki_admin);
        napitki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Напитки");
                startActivity(intent);
            }
        });
        vinnaakarta = findViewById(R.id.vinnyakarta_admin);
        vinnaakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Винная карта");
                startActivity(intent);
            }
        });


    }
}