package com.example.smartrestaurant.Waiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Cook.CookDisplay;
import com.example.smartrestaurant.R;

public class WaiterDisplay extends AppCompatActivity {
    private ImageView back;
    private String zak,kom,tab,sym,pid,bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_display);
        back = findViewById(R.id.back_waiter_display);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterDisplay.this, WaiterActivity.class);
                startActivity(intent);
            }
        });
        Bundle arguments = getIntent().getExtras();
        zak = arguments.get("zakaz").toString();
        kom = arguments.get("koment").toString();
        tab = arguments.get("table").toString();
        sym = arguments.get("symma").toString();
        pid = arguments.get("pid").toString();
       // bar = arguments.get("bar").toString();
        final TextView zakazTextView = (TextView) findViewById(R.id.kyhnya_display_waiter);
        zakazTextView.setText(zak);
        final TextView komentTextView = (TextView) findViewById(R.id.komment_display_waiter);
        komentTextView.setText(kom);
        final TextView tabTextView = (TextView) findViewById(R.id.nom);
        tabTextView.setText(tab);

    }
}