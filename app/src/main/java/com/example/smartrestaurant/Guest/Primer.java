package com.example.smartrestaurant.Guest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Reserved.AddReservedActivity;
import com.example.smartrestaurant.Admin.Reserved.ReservedActivity;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.Products;

import com.example.smartrestaurant.R;

import com.example.smartrestaurant.Entry.LoginActivity;
import com.example.smartrestaurant.Entry.RegisterActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class Primer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back, pokypki,plus,minus;
    private int kolvobl = 1;
    private ProgressDialog loadingBar;
    private String txtname,txtdeck,txtprice,cat, saveCurrentDate, saveCurrentTime, productRandomKey,pid;
    private String valuekolvo;
    DatabaseReference ProductsRef;
    DatabaseReference BasketRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primer);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        recyclerView = findViewById(R.id.recycler_primer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadingBar = new ProgressDialog(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.back_guest_purchase);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        pokypki = findViewById(R.id.pokypka);
        TextView kolvo = findViewById(R.id.kolvo);
        valuekolvo = "1";
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Primer.this, Meny.class);
                intent.putExtra("category", cat);
                startActivity(intent);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kolvobl++;
                valuekolvo = Integer.toString(kolvobl);
                kolvo.setText(valuekolvo);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kolvobl--;
                valuekolvo = Integer.toString(kolvobl);
                kolvo.setText(valuekolvo);
            }
        });
        pokypki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBasket();
                Intent intent = new Intent(Primer.this, Meny.class);
                intent.putExtra("category", cat);
                startActivity(intent);
            }
        });
        Bundle arguments = getIntent().getExtras();
        txtdeck = arguments.get("desk").toString();
        txtprice = arguments.get("price").toString();
        txtname = arguments.get("name").toString();
        cat = arguments.get("cat").toString();
        pid = arguments.get("pid").toString();
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class).build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Products model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText( model.getPrice() + " рублей");
                Picasso.get().load(model.getImage()).into(holder.imageView);

                if (!(model.getPname().equals(txtname))&&!(model.getPrice().equals(txtprice))&&!(model.getDescription().equals(txtdeck))) {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }
            @NonNull
            @NotNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout_primer, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    private void AddBasket() {
        BasketRef = FirebaseDatabase.getInstance().getReference().child(SettingsGuest.numtab);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());
        productRandomKey = saveCurrentDate + saveCurrentTime;

        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("name", txtname);
        productMap.put("price", txtprice);
        productMap.put("valuekolvo", valuekolvo);
        productMap.put("category", cat);
        productMap.put("pid", productRandomKey);

       BasketRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Primer.this, "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                });
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

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtProductName, txtProductDescription, txtProductPrice,id;
        public ImageView imageView;
        public ItemClickListener listner;
        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image_vibor);
            txtProductName = itemView.findViewById(R.id.product_name_vibor);
            txtProductDescription = itemView.findViewById(R.id.product_description_vibor);
            txtProductPrice = itemView.findViewById(R.id.product_price_vibor);
        }
        public void setItemClickListner(ItemClickListener listner) {
            this.listner = listner;
        }

        @Override
        public void onClick(View view) {
            listner.onClick(view, getAdapterPosition(), false);
        }
    }
}