package com.example.smartrestaurant.Entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
  private Button registerButton;
    public EditText nameInput;
    private EditText surnameInput;
    private EditText phoneInput;
    private EditText passwordInput;
    private EditText idInput;
    private Spinner roleInput;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       registerButton = (Button) findViewById(R.id.register_button);
       nameInput = (EditText) findViewById(R.id.register_name_input);
       surnameInput = (EditText) findViewById(R.id.register_surname_input);
       phoneInput = (EditText) findViewById(R.id.register_phone_input);
       passwordInput = (EditText) findViewById(R.id.register_password_input);
       idInput = (EditText) findViewById(R.id.register_id_input);
       roleInput = (Spinner) findViewById(R.id.register_role_input);
       loadingBar = new ProgressDialog(this);
       registerButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               CreateAccount();
           }
       });

    }

    public EditText getNameInput() {
        return nameInput;
    }

    public void setNameInput(EditText nameInput) {
        this.nameInput = nameInput;
    }

    private void CreateAccount()
    {
        String name = nameInput.getText().toString();
        String surname = surnameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();
        String id = idInput.getText().toString();
        String role = roleInput.getSelectedItem().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Введите имя",Toast.LENGTH_SHORT).show();
        }
         else if(TextUtils.isEmpty(surname))
        {
            Toast.makeText(this,"Введите фамилию",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Введите номер телефона",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Введите пароль",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(id))
        {
            Toast.makeText(this,"Введите id",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(role))
        {
            Toast.makeText(this,"Введите роль",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Создание аккаунта");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
             ValidatePhone(name,surname,phone,password,id,role);
        }
    }

    private void ValidatePhone(String name, String surname, String phone, String password, String id, String role)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name", name);
                    userDataMap.put("surname",surname);
                    userDataMap.put("phone",phone);
                    userDataMap.put("password",password);
                    userDataMap.put("id",id);
                    userDataMap.put("role",role);
                    RootRef.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this,"Регистрация прошла успешно",Toast.LENGTH_SHORT).show();
                                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(loginIntent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Номер "+phone+" уже зарегистрирован",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}