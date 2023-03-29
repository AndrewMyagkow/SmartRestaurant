package com.example.smartrestaurant.ZalobiBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Menu.AddFoodActivity;
import com.example.smartrestaurant.Model.Feedback;
import com.example.smartrestaurant.Model.Products;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ViewHolder.FeedbackViewHolder;
import com.example.smartrestaurant.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ZalobiBookActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back,add;
    private String Age;
    private AppBarConfiguration mAppBarConfiguration;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zalobibook);




        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Feedback");


        recyclerView = findViewById(R.id.recycler_menuq);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.back_zalobibook);
        add = findViewById(R.id.add_zalobibook);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZalobiBookActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZalobiBookActivity.this, AddZalobiBook.class);
                startActivity(intent);
            }
        });

        FirebaseRecyclerOptions<Feedback> options = new FirebaseRecyclerOptions.Builder<Feedback>()
                .setQuery(ProductsRef, Feedback.class).build();

        FirebaseRecyclerAdapter<Feedback, FeedbackViewHolder> adapter = new FirebaseRecyclerAdapter<Feedback, FeedbackViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull FeedbackViewHolder holder, int i, @NonNull @NotNull Feedback model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText( model.getPrice());
            }

            @NonNull
            @NotNull
            @Override
            public FeedbackViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zalobibook_items_layout, parent, false);
                FeedbackViewHolder holder = new FeedbackViewHolder(view);
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