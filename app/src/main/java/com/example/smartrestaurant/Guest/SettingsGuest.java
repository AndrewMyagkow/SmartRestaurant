package com.example.smartrestaurant.Guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Admin.SetingsActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.Prevalent.Prevalent;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SettingsGuest extends AppCompatActivity {
private ImageView back,save,exit;
private EditText numbertable;
public static String numtab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_guest);
        back = findViewById(R.id.back_guest_settings);
        save = findViewById(R.id.save_settings);
        exit = findViewById(R.id.exit_guest);
        numbertable = (EditText)findViewById(R.id.numbertable);
        numtab = numbertable.getText().toString();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsGuest.this, GuestActivity.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numtab = numbertable.getText().toString();
                Intent intent = new Intent(SettingsGuest.this, GuestActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Paper.book().write(Prevalent.UsingPhoneKey, "");
                    Paper.book().write(Prevalent.UsingPasswordKey, "");
                    Intent intent = new Intent(SettingsGuest.this, MainActivity.class);
                    startActivity(intent);

            }
        });

    }
}