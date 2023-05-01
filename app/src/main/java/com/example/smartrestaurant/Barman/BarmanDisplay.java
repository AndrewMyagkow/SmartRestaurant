package com.example.smartrestaurant.Barman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Cook.CookDisplay;
import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class BarmanDisplay extends AppCompatActivity {
    private String zak,kom,tab,sym,pid,saveCurrentDate, saveCurrentTime, productRandomKey;

    private ImageView back;
    private Button done;
    private DatabaseReference ProductsRef;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman_display);
        Bundle arguments = getIntent().getExtras();
        zak = arguments.get("zakaz").toString();
        kom = arguments.get("koment").toString();
        tab = arguments.get("table").toString();
        sym = arguments.get("symma").toString();
        pid = arguments.get("pid").toString();
        back = findViewById(R.id.back_bar_display);
        done = findViewById(R.id.done_bar);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanDisplay.this, BarmanActivity.class);
                startActivity(intent);
            }
        });
        final TextView zakazTextView = (TextView) findViewById(R.id.zakaz_display_bar);
        zakazTextView.setText(zak);
        final TextView komentTextView = (TextView) findViewById(R.id.komment_display_bar);
        komentTextView.setText(kom);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductsRef = FirebaseDatabase.getInstance().getReference().child("ReadyOrder");
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                saveCurrentTime = currentTime.format(calendar.getTime());

                productRandomKey = saveCurrentDate + saveCurrentTime;


                SaveProductInfoToDatabase();
                Back();

            }

            private void SaveProductInfoToDatabase() {
                HashMap<String, Object> productMap = new HashMap<>();

                productMap.put("pid", productRandomKey);
                productMap.put("date", saveCurrentDate);
                productMap.put("time", saveCurrentTime);
                productMap.put("zakaz", zak);
                productMap.put("table", tab);
                productMap.put("symma", sym);
                productMap.put("komment", kom);




                ProductsRef.child(productRandomKey).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }

        });
    }

    private void Back() {
        ProductsRef = FirebaseDatabase.getInstance().getReference();
        ProductsRef.child("AccomplishmentBar/"+pid).setValue(null);
        Intent intent = new Intent(BarmanDisplay.this, BarmanActivity.class);
        startActivity(intent);
    }

}