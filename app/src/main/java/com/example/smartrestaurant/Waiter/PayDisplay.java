package com.example.smartrestaurant.Waiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.R;

public class PayDisplay extends AppCompatActivity {
    private ImageView back;
    private String zak,tab,sym,pid,bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_display);
        back = findViewById(R.id.back_waiter_pay_display);
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

    }
}