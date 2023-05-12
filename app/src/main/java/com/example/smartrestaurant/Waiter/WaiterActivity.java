package com.example.smartrestaurant.Waiter;

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
import com.example.smartrestaurant.Guest.SettingsGuest;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.ReadyOrder;
import com.example.smartrestaurant.Model.Zakaz;
import com.example.smartrestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class WaiterActivity extends AppCompatActivity {
    private ImageView writebook,menu,chat,pay;
    private ImageView setings;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        init();


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, Message.class);
                intent.putExtra("role", "Оффициант");
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, SetingsWaiterActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, WriteBookWaiter.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, MenuWaiterActivity.class);
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaiterActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("ReadyOrder");
        recyclerView = findViewById(R.id.recycler_waiter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ReadyOrder> options = new FirebaseRecyclerOptions.Builder<ReadyOrder>()
                .setQuery(ProductsRef, ReadyOrder.class).build();

        FirebaseRecyclerAdapter<ReadyOrder, WaiterActivity.ProductViewHolder> adapter = new FirebaseRecyclerAdapter<ReadyOrder, WaiterActivity.ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull WaiterActivity.ProductViewHolder holder, int i, @NonNull @NotNull ReadyOrder model) {
                if (!model.getZakaz().equals("Подойдите пожалуйста к столу № "))
                {
                    if (!model.getZakaz().equals("Подойдите пожалуйста к администратору")) {
                        holder.txtZakaz.setText(model.getZakaz());
                        holder.txtKomment.setText(model.getKomment());
                        holder.txttable.setText(model.getTable());
                        holder.txtsymma.setText(model.getSymma());
                        holder.txtpid.setText(model.getPid());
                        holder.txtPlace.setText(model.getPlace());
                    }
                    else
                    {
                        holder.txtpid.setText(model.getPid());
                        holder.txtVizov.setText(model.getZakaz());
                        holder.txtTextNum.setText("");

                    }
                }
                else
                {   holder.txtpid.setText(model.getPid());
                    holder.txtVizov.setText(model.getZakaz()+model.getTable());
                    holder.txtTextNum.setText("");

                }

            }


            @NonNull
            @NotNull
            @Override
            public WaiterActivity.ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.waiter_items_layout, parent, false);
                WaiterActivity.ProductViewHolder holder = new WaiterActivity.ProductViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
    private void init()
    {
        writebook = findViewById(R.id.writebookwaiter);
        chat = findViewById(R.id.chatwaiter);
        setings = findViewById(R.id.setingswaiter);
        menu = findViewById(R.id.menuwaiter);
        pay = findViewById(R.id.pay);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtZakaz, txtKomment,txttable, txtsymma,txtpid,txtPlace,txtTextNum,txtVizov;
        public ItemClickListener listner;
        public ProductViewHolder(View itemView) {
            super(itemView);

            txtZakaz = itemView.findViewById(R.id.zakaz_waiter);
            txtKomment= itemView.findViewById(R.id.komment_waiter);
            txttable = itemView.findViewById(R.id.table_waiter);
            txtsymma = itemView.findViewById(R.id.symma_waiter);
            txtpid = itemView.findViewById(R.id.pid_waiter);
            txtPlace = itemView.findViewById(R.id.place_waiter_zakaz);
            txtTextNum = itemView.findViewById(R.id.text_num_table);
            txtVizov = itemView.findViewById(R.id.vizov_waiter);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String zakaz = txtZakaz.getText().toString();
                    if (zakaz.equals(""))
                    {
                        String pid = txtpid.getText().toString();
                        ProductsRef = FirebaseDatabase.getInstance().getReference();
                        ProductsRef.child("ReadyOrder/"+pid).setValue(null);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                    else
                    {
                        Intent intent = new Intent(WaiterActivity.this, WaiterDisplay.class);
                        String koment = txtKomment.getText().toString();
                        String table = txttable.getText().toString();
                        String symma = txtsymma.getText().toString();
                        String pid = txtpid.getText().toString();
                        String place = txtPlace.getText().toString();
                        intent.putExtra("zakaz", zakaz);
                        intent.putExtra("koment", koment);
                        intent.putExtra("table", table);
                        intent.putExtra("symma", symma);
                        intent.putExtra("pid", pid);
                        intent.putExtra("place", place);
                        startActivity(intent);
                    }
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