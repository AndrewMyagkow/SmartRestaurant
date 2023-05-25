package com.example.smartrestaurant.Waiter;

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

public class WaiterDisplay extends AppCompatActivity {
    private ImageView back;
    private String zak,kom,tab,pla,sym,pid,saveCurrentDate, saveCurrentTime;
    private Button expected;
    private DatabaseReference InfoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_display);
        expected = findViewById(R.id.done_waiter);
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
        pla = arguments.get("place").toString();
        sym = arguments.get("symma").toString();
        pid = arguments.get("pid").toString();
        final TextView zakazTextView = (TextView) findViewById(R.id.kyhnya_display_waiter);
        zakazTextView.setText(zak);
        final TextView komentTextView = (TextView) findViewById(R.id.komment_display_waiter);
        komentTextView.setText(kom);
        final TextView tabTextView = (TextView) findViewById(R.id.nom);
        tabTextView.setText(tab);

        expected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoRef = FirebaseDatabase.getInstance().getReference().child("InfoAdmin");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                saveCurrentTime = currentTime.format(calendar.getTime());
                SaveProductInfoToDatabase();
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
                productMap.put("admin", "Ожидается оплата");

                InfoRef.child(pid).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                if (pla.equals("Бар")) {
                    InfoRef = FirebaseDatabase.getInstance().getReference();
                    InfoRef.child("InfoAdmin/B" + pid).setValue(null);
                    InfoRef = FirebaseDatabase.getInstance().getReference();
                    InfoRef.child("ReadyOrder/B" + pid).setValue(null);
                }
                if (pla.equals("Кухня"))
                {
                    InfoRef = FirebaseDatabase.getInstance().getReference();
                    InfoRef.child("InfoAdmin/C" + pid).setValue(null);
                    InfoRef = FirebaseDatabase.getInstance().getReference();
                    InfoRef.child("ReadyOrder/C" + pid).setValue(null);
                }
                Intent intent = new Intent(WaiterDisplay.this, WaiterActivity.class);
                startActivity(intent);
            }

        });
    }
}