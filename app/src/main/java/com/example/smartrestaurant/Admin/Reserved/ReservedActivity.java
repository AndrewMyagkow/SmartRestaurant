package com.example.smartrestaurant.Admin.Reserved;

import static android.app.job.JobInfo.PRIORITY_HIGH;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Menu.AddFoodActivity;
import com.example.smartrestaurant.Model.Products;
import com.example.smartrestaurant.Model.Reserved;
import com.example.smartrestaurant.R;

import com.example.smartrestaurant.ViewHolder.ReservedViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ReservedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back,add;
    private int fl;
    private String date,mounth,year,clock,minuts,addclock,addminuts,info;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_reserved);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Reserved");
        recyclerView = findViewById(R.id.recycler_reserved);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


    }



    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.back_list_reserved);
        add = findViewById(R.id.add_item_reserved);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservedActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservedActivity.this, AddReservedActivity.class);
                startActivity(intent);
            }
        });
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


                    if ((model.getDescription().equals(date)) && (model.getPrice().equals(mounth)) && (model.getPrimer().equals(year))) {
                        holder.txtDateText.setBackgroundResource(R.color.chartreuse);
                    }
                    if ((model.getDescription().equals(date)) && (model.getPrice().equals(mounth)) && (model.getPrimer().equals(year)) && (model.getClock().equals(clock))) {
                        holder.txtTiming.setBackgroundResource(R.color.dark_orange);
                    }
                    if ((model.getDescription().equals(date)) && (model.getPrice().equals(mounth)) && (model.getPrimer().equals(year)) && (model.getClock().equals(clock)) && (model.getMinuts().equals(minuts))) {
                        holder.txtTiming.setBackgroundResource(R.color.red);



                    }
                }


                @NonNull
                @NotNull
                @Override
                public ReservedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserved_items_layout, parent, false);
                    ReservedViewHolder holder = new ReservedViewHolder(view);
                    return holder;

                }

            };


            recyclerView.setAdapter(adapter);
            adapter.startListening();






    }



   @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}