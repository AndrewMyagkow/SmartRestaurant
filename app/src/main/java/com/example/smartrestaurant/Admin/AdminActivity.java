package com.example.smartrestaurant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.Admin.Reserved.ReservedActivity;
import com.example.smartrestaurant.Model.Reserved;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ViewHolder.ReservedViewHolder;
import com.example.smartrestaurant.Admin.ZalobiBook.ZalobiBookActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String date,mounth,year,clock,minuts,info,secund;
    int fl=0;
    private ImageView biznes,writebook,menu,chat,reserved;
    private ImageView setings,zalobi; DatabaseReference ProductsRef;
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


        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Reserved");
        recyclerView = findViewById(R.id.recycler_admin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        handler = new Handler();
        onEverySecond.run();



            FirebaseRecyclerOptions<Reserved> options = new FirebaseRecyclerOptions.Builder<Reserved>()
                    .setQuery(ProductsRef, Reserved.class).build();

            FirebaseRecyclerAdapter<Reserved, ReservedViewHolder> adapter = new FirebaseRecyclerAdapter<Reserved, ReservedViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull @NotNull ReservedViewHolder holder, int i, @NonNull @NotNull Reserved model) {


                    holder.txtProductName.setText(model.getPname());
                    holder.txtProductPrimer.setText(model.getPrimer());
                    holder.txtProductDescription.setText(model.getDescription());
                    holder.txtProductPrice.setText(model.getPrice());
                    holder.txtClock.setText(model.getClock());
                    holder.txtMinuts.setText(model.getMinuts());
                    holder.txtKolvoGuest.setText(model.getKolvoguest());
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

                @NonNull
                @NotNull
                @Override
                public ReservedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                             int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserved_items_layout, parent, false);
                    ReservedViewHolder holder = new ReservedViewHolder(view);
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
                    Intent intent = new Intent(AdminActivity.this, HomeActivity.class);
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