package com.example.smartrestaurant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.Menu.ChoiseMenu;
import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.Admin.Reserved.ReservedActivity;
import com.example.smartrestaurant.Guest.GuestActivity;
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.Model.Reserved;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ViewHolder.InfoAdminViewHolder;
import com.example.smartrestaurant.Admin.ZalobiBook.ZalobiBookActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String date,mounth,year,clock,minuts, pid,saveCurrentDate, saveCurrentTime;
    private ImageView biznes,writebook,menu,chat,reserved,call;
    private ImageView setings,zalobi;
    DatabaseReference InfoRef;
    private DatabaseReference Admin;
    private ProgressDialog loadingBar;
    Handler handler;
    int limit = 40;
    int count = 0;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private NotificationManager notificationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        loadingBar = new ProgressDialog(this);


        InfoRef = FirebaseDatabase.getInstance().getReference().child("InfoAdmin");
        recyclerView = findViewById(R.id.recycler_admin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        handler = new Handler();
        onEverySecond.run();



        FirebaseRecyclerOptions<Reserved> options = new FirebaseRecyclerOptions.Builder<Reserved>()
                .setQuery(InfoRef, Reserved.class).build();

        FirebaseRecyclerAdapter<Reserved, InfoAdminViewHolder> adapter = new FirebaseRecyclerAdapter<Reserved, InfoAdminViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull InfoAdminViewHolder holder, int i, @NonNull @NotNull Reserved model) {

                if(model.getAdmin() == null)
                {
                    holder.txtDateId.setText(model.getDescription()+"."+model.getPrice()+"."+model.getPrimer());
                    holder.txtTimeStatus.setText(model.getClock()+":"+model.getMinuts());
                    holder.txtKolvoGuestNumTab.setText(model.getKolvoguest());
                    holder.txtNameGuest.setText(model.getPname());
                    holder.txtTextName.setText("ФИО");
                    holder.txtTextDateId.setText("Дата");
                    holder.txtTextTimeStatus.setText("Время");
                    holder.txtKolvoNumTab.setText("Кол-во");
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd");
                    date = currentDate.format(calendar.getTime());
                    SimpleDateFormat currentMounth = new SimpleDateFormat("MM");
                    mounth = currentMounth.format(calendar.getTime());
                    SimpleDateFormat currentYear = new SimpleDateFormat("yyyy");
                    year = currentYear.format(calendar.getTime());
                    SimpleDateFormat currentClock = new SimpleDateFormat("HH");
                    clock = currentClock.format(calendar.getTime());
                    SimpleDateFormat currentMinuts = new SimpleDateFormat("mm");
                    minuts = currentMinuts.format(calendar.getTime());
                    if (!(model.getDescription().equals(date)) || !(model.getPrice().equals(mounth)) || !(model.getPrimer().equals(year)) || !(model.getClock().equals(clock)) || !(model.getMinuts().equals(minuts))) {
                        holder.itemView.setVisibility(View.GONE);
                        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

                    }
                }
                else
                {
                    holder.txtDateId.setText(model.getPid());
                    holder.txtTimeStatus.setText(model.getAdmin());
                    holder.txtKolvoGuestNumTab.setText(model.getTable());
                    holder.txtTextDateId.setText("ID");
                    holder.txtTextTimeStatus.setText("Статус");
                    holder.txtKolvoNumTab.setText("№ стола");
                    if(model.getPlace()!=null) {
                        holder.txtTextName.setText("Место");
                        holder.txtNameGuest.setText(model.getPlace());
                    }
                }


            }

            @NonNull
            @NotNull
            @Override
            public InfoAdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                         int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_admin_item_layout, parent, false);
                InfoAdminViewHolder holder = new InfoAdminViewHolder(view);
                return holder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


        init();


        biznes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, BiznesActivity.class);
                startActivity(intent);


            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, WriteBookActivity.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, ChoiseMenu.class);
                intent.putExtra("role", "Администратор");
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, Message.class);
                intent.putExtra("role", "Администратор");
                startActivity(intent);
            }
        });
        reserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, ReservedActivity.class);
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, SetingsActivity.class);
                startActivity(intent);
            }
        });
        zalobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=41;
                Intent intent = new Intent(AdminActivity.this, ZalobiBookActivity.class);
                startActivity(intent);
            }
        });
        call = findViewById(R.id.call_admin);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin = FirebaseDatabase.getInstance().getReference().child("ReadyOrder");
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                saveCurrentTime = currentTime.format(calendar.getTime());
                pid = saveCurrentDate+saveCurrentTime;
                SaveProductInfoToDatabase();
            }

            private void SaveProductInfoToDatabase() {
                HashMap<String, Object> productMap = new HashMap<>();

                productMap.put("pid", pid);
                productMap.put("date", saveCurrentDate);
                productMap.put("time", saveCurrentTime);
                productMap.put("zakaz", "Подойдите пожалуйста к администратору");



                Admin.child(pid).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AdminActivity.this, "Оффициант скоро будет", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        });
            }
        });



    }

    Runnable onEverySecond=new Runnable() {
        public void run() {

            count++;
            if (count == limit) {

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            } else {handler.postDelayed(onEverySecond, 1000);
            }
        }
    };
    private void init ()
    {
        biznes = findViewById(R.id.biznes);
        writebook = findViewById(R.id.writebook);
        menu = findViewById(R.id.menu);
        chat = findViewById(R.id.chat);
        reserved = findViewById(R.id.reserved);
        setings = findViewById(R.id.setings);
        zalobi = findViewById(R.id.zalobi);
    }


    @Override
    public void onBackPressed () {
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        return true;
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){
        return false;
    }

}