package com.example.smartrestaurant.Waiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.ReadyOrder;
import com.example.smartrestaurant.Model.Zakaz;
import com.example.smartrestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class PayActivity extends AppCompatActivity {
    private ImageView back;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        back = findViewById(R.id.back_waiter_pay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, WaiterActivity.class);
                startActivity(intent);
            }
        });
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Pay");
        recyclerView = findViewById(R.id.recycler_waiter);
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

        FirebaseRecyclerAdapter<Zakaz, PayActivity.ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Zakaz, PayActivity.ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull PayActivity.ProductViewHolder holder, int i, @NonNull @NotNull Zakaz model) {
                holder.txtZakaz.setText(model.getDishes());
                holder.txttable.setText(model.getTable());
                holder.txtsymma.setText(model.getSymma());
                holder.txtpid.setText(model.getPid());
                holder.txtBarman.setText(model.getBarman());
            }

            @NonNull
            @NotNull
            @Override
            public PayActivity.ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.waiter_pay_items_layout, parent, false);
                PayActivity.ProductViewHolder holder = new PayActivity.ProductViewHolder(view);
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
        public TextView txtZakaz, txttable, txtsymma,txtpid,txtBarman;
        public ItemClickListener listner;
        public ProductViewHolder(View itemView) {
            super(itemView);

            txtZakaz = itemView.findViewById(R.id.zakaz_waiter_pay);
            txttable = itemView.findViewById(R.id.table_waiter_pay);
            txtsymma = itemView.findViewById(R.id.symma_waiter_pay);
            txtpid = itemView.findViewById(R.id.id_zakaz);
            txtBarman = itemView.findViewById(R.id.barman_waiter_pay);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(PayActivity.this, PayDisplay.class);
                    String zakaz = txtZakaz.getText().toString();
                    String table = txttable.getText().toString();
                    String symma = txtsymma.getText().toString();
                    String pid = txtpid.getText().toString();
                    String bar = txtBarman.getText().toString();
                    intent.putExtra("zakaz", zakaz);
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