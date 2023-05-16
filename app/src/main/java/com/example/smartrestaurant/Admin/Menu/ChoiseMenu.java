package com.example.smartrestaurant.Admin.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Guest.Basket;
import com.example.smartrestaurant.Guest.GuestActivity;
import com.example.smartrestaurant.Guest.GuestZalobiBook;
import com.example.smartrestaurant.Guest.Meny;
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.Waiter.WaiterActivity;

public class ChoiseMenu extends AppCompatActivity {
    private ImageView salaty,hotbluda,syp,deserty,zakyski,garniry,fastfud,napitki,vinnaakarta,back,add;
    private TextView addposition;
    private  String Role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_menu);
        TextView addposition = (TextView)findViewById(R.id.textaddposition);
        Bundle arguments = getIntent().getExtras();
        add = findViewById(R.id.add_food_admin);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChoiseMenu.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        Role = arguments.get("role").toString();
        if (Role.equals("Повар")||Role.equals("Бармэн")||Role.equals("Оффициант"))
        {
            int width = 20;
            int height = 20;
            RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
            add.setLayoutParams(parms);
            addposition.setText("");
        }

        back = findViewById(R.id.back_choise_menu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Role.equals("Администратор")) {
                    Intent intent = new Intent(ChoiseMenu.this, AdminActivity.class);
                    startActivity(intent);
                }
                if (Role.equals("Повар")) {
                    Intent intent = new Intent(ChoiseMenu.this, CookActivity.class);
                    startActivity(intent);
                }
                if (Role.equals("Бармэн")) {
                    Intent intent = new Intent(ChoiseMenu.this, BarmanActivity.class);
                    startActivity(intent);
                }
                if (Role.equals("Оффициант")) {
                    Intent intent = new Intent(ChoiseMenu.this, WaiterActivity.class);
                    startActivity(intent);
                }
            }
        });


        salaty = findViewById(R.id.salat_admin);
        salaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Салаты");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        hotbluda = findViewById(R.id.hotbluda_admin);
        hotbluda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Горячие блюда");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        syp = findViewById(R.id.syp_admin);
        syp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Супы");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        deserty = findViewById(R.id.desert_admin);
        deserty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Десерты");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        zakyski = findViewById(R.id.holodnzakyski_admin);
        zakyski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Закуски");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        garniry = findViewById(R.id.garniry_admin);
        garniry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Гарниры");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        fastfud = findViewById(R.id.fastfyd_admin);
        fastfud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "ФастФуд");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        napitki = findViewById(R.id.napitki_admin);
        napitki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Напитки");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });
        vinnaakarta = findViewById(R.id.vinnyakarta_admin);
        vinnaakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiseMenu.this, HomeActivity.class);
                intent.putExtra("category", "Винная карта");
                intent.putExtra("role", Role);
                startActivity(intent);
            }
        });


    }
}