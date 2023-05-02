package com.example.smartrestaurant.Waiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PayDisplay extends AppCompatActivity {
    private ImageView back;
    private String zak,tab,sym,pid,bar,saveCurrentDate,saveCurrentTime;
    private Button paidfor;
    private DatabaseReference PaidFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_display);
        back = findViewById(R.id.back_waiter_pay_display);
        paidfor = findViewById(R.id.paid_for);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayDisplay.this, PayActivity.class);
                startActivity(intent);
            }
        });
        Bundle arguments = getIntent().getExtras();
        zak = arguments.get("zakaz").toString();
        bar = arguments.get("bar").toString();
        tab = arguments.get("table").toString();
        sym = arguments.get("symma").toString();
        pid = arguments.get("pid").toString();
        final TextView zakazTextView = (TextView) findViewById(R.id.zakaz_display_pay);
        zakazTextView.setText(zak);
        final TextView tabTextView = (TextView) findViewById(R.id.nom_table);
        tabTextView.setText(tab);
        final TextView barTextView = (TextView) findViewById(R.id.bar_display_pay);
        barTextView.setText(bar);
        final TextView symTextView = (TextView) findViewById(R.id.itogpay);
        symTextView.setText(sym);
        final TextView pidTextView = (TextView) findViewById(R.id.id_pay);
        pidTextView.setText(pid);

        paidfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaidFor = FirebaseDatabase.getInstance().getReference().child("PaidFor");
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                saveCurrentTime = currentTime.format(calendar.getTime());

                HashMap<String, Object> productMap = new HashMap<>();

                productMap.put("pid", pid);
                productMap.put("date", saveCurrentDate);
                productMap.put("time", saveCurrentTime);
                productMap.put("zakaz", zak);
                productMap.put("table", tab);
                productMap.put("symma", sym);
                productMap.put("bar", bar);
                productMap.put("admin", "Оплачено");


                PaidFor.child(pid).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                PaidFor= FirebaseDatabase.getInstance().getReference();
                PaidFor.child("Pay/" + pid).setValue(null);
                PaidFor= FirebaseDatabase.getInstance().getReference();
                PaidFor.child("InfoAdmin/" + pid).setValue(null);
                Intent intent = new Intent(PayDisplay.this, PayActivity.class);

                startActivity(intent);

            }

        });
    }
}