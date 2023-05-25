package com.example.smartrestaurant.Cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Barman.SetingsBarmanActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.Prevalent.Prevalent;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.Waiter.SetingsWaiterActivity;

import io.paperdb.Paper;

public class SetingsCookActivity extends AppCompatActivity {
    private RelativeLayout settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_cook);
        Button cookOutBtn = findViewById(R.id.CookExitBtn);
        settings = findViewById(R.id.settings_cook);
        cookOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Paper.book().write(Prevalent.UsingPhoneKey, "");
                    Paper.book().write(Prevalent.UsingPasswordKey, "");
                    Intent intent = new Intent(SetingsCookActivity.this, MainActivity.class);
                    startActivity(intent);


            }
        });
    }
}