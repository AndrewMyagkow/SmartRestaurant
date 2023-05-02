package com.example.smartrestaurant.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Barman.BarmanDisplay;
import com.example.smartrestaurant.R;

public class BiznesDiisplay extends AppCompatActivity {
    private ImageView back;
    private String table,symma,pid,kitchen,bar,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biznes_diisplay);
        Bundle arguments = getIntent().getExtras();
        table = arguments.get("table").toString();
        symma = arguments.get("symma").toString();
        kitchen= arguments.get("kitchen").toString();
        bar = arguments.get("bar").toString();
        date = arguments.get("date").toString();
        pid = arguments.get("pid").toString();
        back = findViewById(R.id.back_biznes_display);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BiznesDiisplay.this, BiznesActivity.class);
                startActivity(intent);
            }
        });
        final TextView tableTextView = (TextView) findViewById(R.id.table_zakaz_display);
        tableTextView.setText(table);
        final TextView symmaTextView = (TextView) findViewById(R.id.symma_zakaz_display);
        symmaTextView.setText(symma);
        final TextView kitchenTextView = (TextView) findViewById(R.id.kithen_zakaz_display);
        kitchenTextView.setText(kitchen);
        final TextView barTextView = (TextView) findViewById(R.id.bar_zakaz_display);
        barTextView.setText(bar);
        final TextView dateTextView = (TextView) findViewById(R.id.date_zakaz_display);
        dateTextView.setText(date);
        final TextView pidTextView = (TextView) findViewById(R.id.ID_zakaz_display);
        pidTextView.setText(pid);
    }
}
