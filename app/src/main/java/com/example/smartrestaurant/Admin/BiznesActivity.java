package com.example.smartrestaurant.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Cook.CookDisplay;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.PaidForBiznes;
import com.example.smartrestaurant.Model.ReadyOrder;
import com.example.smartrestaurant.Model.Zakaz;
import com.example.smartrestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class BiznesActivity extends AppCompatActivity {
    private ImageView back;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biznes);
        back = findViewById(R.id.back_biznes_admin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BiznesActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("PaidFor");
        recyclerView = findViewById(R.id.recycler_biznes_admin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<PaidForBiznes> options = new FirebaseRecyclerOptions.Builder<PaidForBiznes>()
                .setQuery(ProductsRef, PaidForBiznes.class).build();

        FirebaseRecyclerAdapter<PaidForBiznes, BiznesActivity.ProductViewHolder> adapter = new FirebaseRecyclerAdapter<PaidForBiznes, BiznesActivity.ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull BiznesActivity.ProductViewHolder holder, int i, @NonNull @NotNull PaidForBiznes model) {
                holder.txtKitchen.setText(model.getZakaz());
                holder.txtBar.setText(model.getBar());
                holder.txtDate.setText(model.getTime()+" "+model.getDate());
                holder.txtTable.setText(model.getTable());
                holder.txtSymma.setText(model.getSymma());
                holder.txtpid.setText(model.getPid());

            }

            @NonNull
            @NotNull
            @Override
            public BiznesActivity.ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biznes_admin_items_layout, parent, false);
                BiznesActivity.ProductViewHolder holder = new BiznesActivity.ProductViewHolder(view);
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


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtpid,txtDate,txtBar,txtKitchen,txtSymma,txtTable;
        public ItemClickListener listner;
        public ProductViewHolder(View itemView) {
            super(itemView);

            txtpid = itemView.findViewById(R.id.id_zakaz_biznes);
            txtDate = itemView.findViewById(R.id.date_zakaz_biznes);
            txtBar = itemView.findViewById(R.id.bar_zakaz_biznes);
            txtKitchen = itemView.findViewById(R.id.kithen_zakaz_biznes);
            txtSymma = itemView.findViewById(R.id.symma_zakaz_biznes);
            txtTable = itemView.findViewById(R.id.table_zakaz_biznes);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(BiznesActivity.this, BiznesDiisplay.class);
                    String kitchen = txtKitchen.getText().toString();
                    String bar = txtBar.getText().toString();
                    String symma = txtSymma.getText().toString();
                    String table = txtTable.getText().toString();
                    String date = txtDate.getText().toString();
                    String pid = txtpid.getText().toString();

                    intent.putExtra("table", table);
                    intent.putExtra("symma", symma);
                    intent.putExtra("pid", pid);
                    intent.putExtra("kitchen", kitchen);
                    intent.putExtra("bar", bar);
                    intent.putExtra("date", date);

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