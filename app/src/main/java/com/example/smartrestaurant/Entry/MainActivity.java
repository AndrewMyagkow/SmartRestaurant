package com.example.smartrestaurant.Entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;


import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Guest.GuestActivity;
import com.example.smartrestaurant.Model.Users;
import com.example.smartrestaurant.Prevalent.Prevalent;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.Waiter.WaiterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button joinButton = (Button) findViewById(R.id.main_join_button);
        Button loginButton = (Button) findViewById(R.id.main_login_button);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });
        joinButton.setOnClickListener(view -> {
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
        String UsingPhoneKey = Paper.book().read(Prevalent.UsingPhoneKey);
        String UsingPasswordKey = Paper.book().read(Prevalent.UsingPasswordKey);
        if(!Objects.equals(UsingPhoneKey, "") && !Objects.equals(UsingPasswordKey, ""))
        {
            if(!TextUtils.isEmpty(UsingPhoneKey)&&!TextUtils.isEmpty(UsingPasswordKey))
            {
                ValidateUsing(UsingPhoneKey,UsingPasswordKey);
                loadingBar.setTitle("Вход");
                loadingBar.setMessage("Пожалуйста, подождите...");
                loadingBar.setCanceledOnTouchOutside(false);
               // loadingBar.show();
            }
        }
        }

    private void ValidateUsing(String phone, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists())
                {


                    Users usersData = snapshot.child("Users").child(phone).getValue(Users.class);

                    assert usersData != null;
                    if(usersData.getPhone().equals(phone))
                    {

                        loadingBar.dismiss();
                        if(usersData.getPassword().equals(password))
                        {

                            Toast.makeText(MainActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                            if(usersData.getRole().equals("Администратор")) {
                                Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                                startActivity(adminIntent);
                            }
                            if(usersData.getRole().equals("Официант")) {
                                Intent waiterIntent = new Intent(MainActivity.this, WaiterActivity.class);
                                startActivity(waiterIntent);
                            }
                            if(usersData.getRole().equals("Повар")) {
                                Intent cookIntent = new Intent(MainActivity.this, CookActivity.class);
                                startActivity(cookIntent);
                            }
                            if(usersData.getRole().equals("Бармэн")) {
                                Intent barmanIntent = new Intent(MainActivity.this, BarmanActivity.class);
                                startActivity(barmanIntent);
                            }
                            if(usersData.getRole().equals("Гость")) {
                                Intent guestIntent = new Intent(MainActivity.this, GuestActivity.class);
                                startActivity(guestIntent);
                            }


                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }



