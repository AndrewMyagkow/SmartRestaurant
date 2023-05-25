package com.example.smartrestaurant.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.Prevalent.Prevalent;
import com.example.smartrestaurant.R;

import io.paperdb.Paper;

public class SetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        Button adminOutBtn = findViewById(R.id.AdminExitBtn);
        adminOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Paper.book().write(Prevalent.UsingPhoneKey, "");
                    Paper.book().write(Prevalent.UsingPasswordKey, "");
                    Intent intent = new Intent(SetingsActivity.this, MainActivity.class);
                    startActivity(intent);

            }
        });
    }
}