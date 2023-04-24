package com.example.smartrestaurant.Admin.Reserved;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class AddReservedActivity extends AppCompatActivity {

    private String categoryName, Description, Price, Pname, saveCurrentDate, saveCurrentTime, productRandomKey, Primer,Clock,Minuts,KolvoGuest,provdate,provmounth,provyear;
    private String downloadImageUrl;
    private Switch dates;
    private ImageView productImage,back;
    private int flag;
    private EditText productName, productDescription, productPrice, productPrimer,productClock,productMinuts,productKolvoGuest;
    private Button addNewProductButton;
    private static final int GALLERYPICK = 1;
    private Uri ImageUri;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reserved_food);

        init();
        back = findViewById(R.id.back_add_reserved);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddReservedActivity.this, ReservedActivity.class);
                startActivity(intent);
            }
        });
        dates = findViewById(R.id.switch1);
       dates.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              flag =1;
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
        Description = productDescription.getText().toString();
        Price = productPrice.getText().toString();
        Pname = productName.getText().toString();
        Primer = productPrimer.getText().toString();
        Clock = productClock.getText().toString();
        Minuts = productMinuts.getText().toString();
        KolvoGuest = productKolvoGuest.getText().toString();

            ResivingDate();
            StoreProductInformation();

    }

    private void ResivingDate() {
        if (flag==1)
        {   Calendar calendar = Calendar.getInstance();

            SimpleDateFormat Date = new SimpleDateFormat("dd");
            provdate = Date.format(calendar.getTime());
            SimpleDateFormat Mounth = new SimpleDateFormat("MM");
            provmounth = Mounth.format(calendar.getTime());
            SimpleDateFormat Year = new SimpleDateFormat("yyyy");
            provyear = Year.format(calendar.getTime());
            Description =provdate;
            Price = provmounth;
            Primer = provyear;
            flag =0;
        }
    }

    private void StoreProductInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


                            SaveProductInfoToDatabase();

    }

    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("category", categoryName);
        productMap.put("price", Price);
        productMap.put("pname", Pname);
        productMap.put("primer",Primer);
        productMap.put("clock",Clock);
        productMap.put("minuts",Minuts);
        productMap.put("kolvoguest",KolvoGuest);


        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){



                            Intent loginIntent = new Intent(AddReservedActivity.this, ReservedActivity.class);
                            startActivity(loginIntent);
                        }
                        else {
                            String message = task.getException().toString();
                            Toast.makeText(AddReservedActivity.this, "Ошибка: "+ message, Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                        }
                    }
                });
    }


    private void init() {

        productName = findViewById(R.id.namereservedguest);
        productDescription = findViewById(R.id.date);
        productPrice = findViewById(R.id.mounth);
        addNewProductButton = findViewById(R.id.btn_add_new_reserved);
        productPrimer = findViewById(R.id.year);
        productClock = findViewById(R.id.clock);
        productMinuts = findViewById(R.id.minuts);
        productKolvoGuest = findViewById(R.id.kolvoguest);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Reserved");
        loadingBar = new ProgressDialog(this);

    }
}