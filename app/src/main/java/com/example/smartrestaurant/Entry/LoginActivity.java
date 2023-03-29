package com.example.smartrestaurant.Entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private String name;
    private EditText phoneInput, passwordInput;
    private ProgressDialog loadingBar;
    private final String parentDBName = "Users";
    private CheckBox CheckBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.login_button);
        phoneInput = (EditText) findViewById(R.id.login_phone_input);
        passwordInput = (EditText) findViewById(R.id.login_password_input);

        loadingBar = new ProgressDialog(this);
        CheckBoxRememberMe = (CheckBox)findViewById(R.id.login_checkbox);
        Paper.init(this);
        loginButton.setOnClickListener(view -> loginUser());
    }
        private void loginUser()
        {
            String phone = phoneInput.getText().toString();
            String password = passwordInput.getText().toString();
            if(TextUtils.isEmpty(phone))
            {
                Toast.makeText(this,"Введите номер телефона",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(password))
            {
                Toast.makeText(this,"Введите пароль",Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadingBar.setTitle("Вход");
                loadingBar.setMessage("Пожалуйста, подождите...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                ValidateUser(phone,password);
            }
        }

    private void ValidateUser(String phone, String password) {
        if(CheckBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.UsingPhoneKey,phone);
            Paper.book().write(Prevalent.UsingPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDBName).child(phone).exists())
                {


                    Users usersData = snapshot.child(parentDBName).child(phone).getValue(Users.class);

                    assert usersData != null;
                    if(usersData.getPhone().equals(phone))
                    {

                        if(usersData.getPassword().equals(password))
                        {

                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                            if(usersData.getRole().equals("Администратор")) {
                                Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(adminIntent);
                            }
                            if(usersData.getRole().equals("Официант")) {
                                Intent waiterIntent = new Intent(LoginActivity.this, WaiterActivity.class);
                                startActivity(waiterIntent);
                            }
                            if(usersData.getRole().equals("Повар")) {
                                Intent cookIntent = new Intent(LoginActivity.this, CookActivity.class);
                                startActivity(cookIntent);
                            }
                            if(usersData.getRole().equals("Бармэн")) {
                                Intent barmanIntent = new Intent(LoginActivity.this, BarmanActivity.class);
                                startActivity(barmanIntent);
                            }
                            if(usersData.getRole().equals("Гость")) {
                                Intent guestIntent = new Intent(LoginActivity.this, GuestActivity.class);
                                startActivity(guestIntent);
                            }


                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Неверный пароль или телефон", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this,"Аккаунт с номером "+phone+" не существует", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}