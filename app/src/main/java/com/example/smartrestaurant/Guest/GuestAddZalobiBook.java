package com.example.smartrestaurant.Guest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class GuestAddZalobiBook extends AppCompatActivity {

    private String stAge, stFeedback, stName, saveCurrentDate, saveCurrentTime, productRandomKey,dates,times, stTime;
    private ImageView back,addNewProductButton;
    private EditText Name, Age, FeedBack;

    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_add_zalobi_book);

        init();

        back = findViewById(R.id.back_guest_zalobibook_add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestAddZalobiBook.this, GuestZalobiBook.class);
                startActivity(intent);
            }
        });
        addNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });
    }

    private void ValidateProductData() {
        stAge = Age.getText().toString();
        stFeedback = FeedBack.getText().toString();
        stName = Name.getText().toString();



        if(TextUtils.isEmpty(stAge)){
            Toast.makeText(this, "Добавьте ваш возраст.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(stFeedback)){
            Toast.makeText(this, "Добавьте отзыв.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(stName)){
            Toast.makeText(this, "Добавьте имя.", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {

        loadingBar.setTitle("Загрузка данных");
        loadingBar.setMessage("Пожалуйста, подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat Datez = new SimpleDateFormat("dd.MM.yyyy");
        dates = Datez.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());
        SimpleDateFormat Timez = new SimpleDateFormat("HH:mm ");
        times = Timez.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;
        stTime = times + dates;
        SaveProductInfoToDatabase();
    }

    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();
        stName = stName+" Возраст: "+ stAge;
        productMap.put("feedback", stFeedback);
        productMap.put("name", stName);
        productMap.put("time", stTime);

        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            loadingBar.dismiss();
                            Toast.makeText(GuestAddZalobiBook.this, "Отзыв добавлен", Toast.LENGTH_SHORT).show();

                            Intent loginIntent = new Intent(GuestAddZalobiBook.this, GuestZalobiBook.class);
                            startActivity(loginIntent);
                        }
                        else {
                            String message = task.getException().toString();
                            Toast.makeText(GuestAddZalobiBook.this, "Ошибка: "+ message, Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                        }
                    }
                });
    }



    private void init() {
        Name = findViewById(R.id.name_guest_add);
        Age = findViewById(R.id.age_guest_add);
        FeedBack = findViewById(R.id.feedback_add);
        addNewProductButton = findViewById(R.id.btn_add_new_feedback_guest);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
        loadingBar = new ProgressDialog(this);

    }
}