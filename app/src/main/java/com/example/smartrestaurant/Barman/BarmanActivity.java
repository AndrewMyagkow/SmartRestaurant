package com.example.smartrestaurant.Barman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrestaurant.Admin.Menu.ChoiseMenu;
import com.example.smartrestaurant.Admin.Menu.HomeActivity;
import com.example.smartrestaurant.Admin.Message.Message;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Cook.CookDisplay;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.Zakaz;
import com.example.smartrestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class BarmanActivity extends AppCompatActivity {
    private ImageView writebook,chat,menu,setings;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barman);
        init();
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, Message.class);
                intent.putExtra("role", "Бармэн");
                startActivity(intent);
            }
        });
        setings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, SetingsBarmanActivity.class);
                startActivity(intent);
            }
        });
        writebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, WriteBookBarman.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarmanActivity.this, ChoiseMenu.class);
                intent.putExtra("role", "Бармэн");
                startActivity(intent);
            }
        });
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("AccomplishmentBar");
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

        FirebaseRecyclerAdapter<Zakaz, BarmanActivity.ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Zakaz, BarmanActivity.ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull BarmanActivity.ProductViewHolder holder, int i, @NonNull @NotNull Zakaz model) {
                holder.txtZakaz.setText(model.getBarman());
                holder.txtKomment.setText(model.getKomment());
                holder.txttable.setText(model.getTable());
                holder.txtsymma.setText(model.getSymma());
                holder.txtpid.setText(model.getPid());
                if (model.getBarman().equals(""))
                {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @NotNull
            @Override
            public BarmanActivity.ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_items_layout, parent, false);
                BarmanActivity.ProductViewHolder holder = new BarmanActivity.ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    private void init()
    {
        writebook = findViewById(R.id.writebookbarman);
        chat = findViewById(R.id.chatbarman);
        setings = findViewById(R.id.setingsbarman);
        menu = findViewById(R.id.menubarman);
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtZakaz, txtKomment,txttable, txtsymma,txtpid;
        public ItemClickListener listner;
        private ImageView done;


        public ProductViewHolder(View itemView) {
            super(itemView);

            txtZakaz = itemView.findViewById(R.id.zakaz_cook);
            txtKomment= itemView.findViewById(R.id.komment_cook);
            txttable = itemView.findViewById(R.id.table);
            txtsymma = itemView.findViewById(R.id.symma);
            txtpid = itemView.findViewById(R.id.pid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BarmanActivity.this,BarmanDisplay.class);
                    String zakaz = txtZakaz.getText().toString();
                    String koment = txtKomment.getText().toString();
                    String table = txttable.getText().toString();
                    String symma = txtsymma.getText().toString();
                    String pid = txtpid.getText().toString();
                    intent.putExtra("zakaz", zakaz);
                    intent.putExtra("koment", koment);
                    intent.putExtra("table", table);
                    intent.putExtra("symma", symma);
                    intent.putExtra("pid", pid);
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