package com.example.smartrestaurant.Admin.Menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.Products;

import com.example.smartrestaurant.R;

import com.example.smartrestaurant.Entry.LoginActivity;
import com.example.smartrestaurant.Entry.RegisterActivity;
import com.example.smartrestaurant.Waiter.WaiterActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back;
    private String Category,Role;
    static DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Bundle arguments = getIntent().getExtras();
        Category = arguments.get("category").toString();
        Role = arguments.get("role").toString();
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Role.equals("Администратор")) {
                    Intent intent = new Intent(HomeActivity.this, ChoiseMenu.class);
                    intent.putExtra("role", Role);
                    startActivity(intent);
                }
                if (Role.equals("Повар")) {
                    Intent intent = new Intent(HomeActivity.this, ChoiseMenu.class);
                    intent.putExtra("role", Role);
                    startActivity(intent);
                }
                if (Role.equals("Бармэн")) {
                    Intent intent = new Intent(HomeActivity.this, ChoiseMenu.class);
                    intent.putExtra("role", Role);
                    startActivity(intent);
                }
                if (Role.equals("Оффициант")) {
                    Intent intent = new Intent(HomeActivity.this, ChoiseMenu.class);
                    intent.putExtra("role", Role);
                    startActivity(intent);
                }
            }
        });

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Products model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtID.setText(model.getPid());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText(model.getPrice() + " рублей");
                Picasso.get().load(model.getImage()).into(holder.imageView);
                if (!model.getCategory().equals(Category)) {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @NotNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
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
    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtProductName, txtProductDescription, txtProductPrice, txtID, txtProductCategory;
        public ImageView imageView,delet;
        public ItemClickListener listner;
        private Context context;


        public ProductViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.product_image);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductDescription = itemView.findViewById(R.id.product_description);
            txtProductPrice = itemView.findViewById(R.id.product_price);
            txtProductCategory = itemView.findViewById(R.id.add_category);
            txtID = itemView.findViewById(R.id.pid_food);
            delet =itemView.findViewById(R.id.delet_food);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* int position = getAdapterPosition();
                    Intent adminIntent = new Intent(context, AdminActivity.class);
                    context.startActivity(adminIntent);*/
                }
            });
            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String pid = txtID.getText().toString();
                    ProductsRef = FirebaseDatabase.getInstance().getReference();
                    ProductsRef.child("Products/"+pid).setValue(null);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });
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