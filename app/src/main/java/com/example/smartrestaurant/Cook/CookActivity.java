package com.example.smartrestaurant.Cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Menu.AddFoodActivity;
import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;

import com.example.smartrestaurant.Entry.LoginActivity;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.Products;
import com.example.smartrestaurant.Model.Zakaz;
import com.example.smartrestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class CookActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView writebook,chat;
    private ImageView setings;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        init();


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, Message.class);
                intent.putExtra("role", "Повар");
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, SetingsCookActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookActivity.this, WriteBookCook.class);
                startActivity(intent);
            }
        });



        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Accomplishment");
        recyclerView = findViewById(R.id.recycler_cook);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Zakaz> options = new FirebaseRecyclerOptions.Builder<Zakaz>()
                .setQuery(ProductsRef, Zakaz.class).build();

        FirebaseRecyclerAdapter<Zakaz, CookActivity.ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Zakaz, CookActivity.ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CookActivity.ProductViewHolder holder, int i, @NonNull @NotNull Zakaz model) {
                holder.txtZakaz.setText(model.getDishes());
                holder.txtKomment.setText(model.getKomment());
                holder.txttable.setText(model.getTable());
                holder.txtsymma.setText(model.getSymma());
                holder.txtpid.setText(model.getPid());
                if (model.getDishes().equals(""))
                {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @NotNull
            @Override
            public CookActivity.ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_items_layout, parent, false);
                CookActivity.ProductViewHolder holder = new CookActivity.ProductViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
    private void init()
    {
        writebook = findViewById(R.id.writebookcook);
        chat = findViewById(R.id.chatcook);
        setings = findViewById(R.id.setingscook);
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
        public TextView txtZakaz, txtKomment,txttable, txtsymma,txtpid,txtBar;
        public ItemClickListener listner;
        private ImageView done;


        public ProductViewHolder(View itemView) {
            super(itemView);

            txtZakaz = itemView.findViewById(R.id.zakaz_cook);
            txtKomment= itemView.findViewById(R.id.komment_cook);
            txttable = itemView.findViewById(R.id.table);
            txtsymma = itemView.findViewById(R.id.symma);
            txtpid = itemView.findViewById(R.id.pid);
            txtBar = itemView.findViewById(R.id.bar_cook);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(CookActivity.this, CookDisplay.class);
                    String zakaz = txtZakaz.getText().toString();
                    String koment = txtKomment.getText().toString();
                    String table = txttable.getText().toString();
                    String symma = txtsymma.getText().toString();
                    String pid = txtpid.getText().toString();
                    String bar = txtBar.getText().toString();
                    intent.putExtra("zakaz", zakaz);
                    intent.putExtra("koment", koment);
                    intent.putExtra("table", table);
                    intent.putExtra("symma", symma);
                    intent.putExtra("pid", pid);
                    intent.putExtra("bar", bar);
                    startActivity(intent);
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