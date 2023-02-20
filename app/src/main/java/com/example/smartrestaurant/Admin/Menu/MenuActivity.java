package com.example.smartrestaurant.Admin.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Model.Products;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.VievHolder.ProductVievHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuActivity extends AppCompatActivity {
    private ImageView back,add;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class).build();
        FirebaseRecyclerAdapter<Products, ProductVievHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductVievHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductVievHolder holder, int position, @NonNull Products model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDiscription());
                holder.txtProductPrice.setText(model.getPrice());
            }

            @NonNull
            @Override
            public ProductVievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent,false);
               ProductVievHolder holder = new ProductVievHolder(view);
                return null;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void init()
    {
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);}

}