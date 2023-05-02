package com.example.smartrestaurant.Cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.Reserved.AddReservedActivity;
import com.example.smartrestaurant.Admin.Reserved.ReservedActivity;
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CookDisplay extends AppCompatActivity {
    private String zak,kom,tab,sym,pid,bar,saveCurrentDate, saveCurrentTime, productRandomKey;

    private ImageView back;
    private Button done;
    private DatabaseReference ProductsRef;
    private DatabaseReference InfoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_display);
        Bundle arguments = getIntent().getExtras();
        zak = arguments.get("zakaz").toString();
        kom = arguments.get("koment").toString();
        tab = arguments.get("table").toString();
        sym = arguments.get("symma").toString();
        pid = arguments.get("pid").toString();
        bar = arguments.get("bar").toString();
        back = findViewById(R.id.back_cook_display);
        done = findViewById(R.id.done_cook);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookDisplay.this, CookActivity.class);
                startActivity(intent);
            }
        });
        final TextView zakazTextView = (TextView) findViewById(R.id.zakaz_display);
        zakazTextView.setText(zak);
        final TextView komentTextView = (TextView) findViewById(R.id.komment_display);
        komentTextView.setText(kom);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductsRef = FirebaseDatabase.getInstance().getReference().child("ReadyOrder");
                InfoRef = FirebaseDatabase.getInstance().getReference().child("InfoAdmin");
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                saveCurrentTime = currentTime.format(calendar.getTime());

                productRandomKey ="C"+pid;


                SaveProductInfoToDatabase();
                Back();

            }

            private void SaveProductInfoToDatabase() {
                HashMap<String, Object> productMap = new HashMap<>();

                productMap.put("pid", pid);
                productMap.put("date", saveCurrentDate);
                productMap.put("time", saveCurrentTime);
                productMap.put("zakaz", zak);
                productMap.put("table", tab);
                productMap.put("symma", sym);
                productMap.put("komment", kom);
                productMap.put("admin", "Готово");
                productMap.put("place", "Кухня");




                ProductsRef.child(productRandomKey).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                InfoRef.child(productRandomKey).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                InfoRef = FirebaseDatabase.getInstance().getReference();
                InfoRef.child("InfoAdmin/"+pid).setValue(null);
            }

        });
    }

    private void Back() {
        ProductsRef = FirebaseDatabase.getInstance().getReference();
        ProductsRef.child("Accomplishment/"+pid).setValue(null);
        Intent intent = new Intent(CookDisplay.this, CookActivity.class);
        startActivity(intent);
    }
}