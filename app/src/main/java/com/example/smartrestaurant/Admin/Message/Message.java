package com.example.smartrestaurant.Admin.Message;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Barman.BarmanActivity;
import com.example.smartrestaurant.Cook.CookActivity;
import com.example.smartrestaurant.Model.Chat;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ViewHolder.MessageViewHolder;
import com.example.smartrestaurant.Waiter.WaiterActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Message extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back,deletemessage;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    private String  Description, Role, Pname, saveCurrentDate, saveCurrentTime, productRandomKey,TimeSms;
    private EditText productName;
    private ImageView addNewProductButton;
    private DatabaseReference ProductsRefs;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_message);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Chat");
        recyclerView = findViewById(R.id.recycler_menuz);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        init();

        addNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.backz);

       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (Role) {
                    case "Администратор": {
                        Intent intent = new Intent(Message.this, AdminActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Повар": {
                        Intent intent = new Intent(Message.this, CookActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Бармэн": {
                        Intent intent = new Intent(Message.this, BarmanActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Оффициант": {
                        Intent intent = new Intent(Message.this, WaiterActivity.class);
                        startActivity(intent);
                        break;
                    }
                }


            }
        });


        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(ProductsRef, Chat.class).build();

        FirebaseRecyclerAdapter<Chat, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<Chat, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull MessageViewHolder holder, int i, @NonNull @NotNull Chat model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText( model.getPrice());


            }

            @NonNull
            @NotNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_items_layout, parent, false);
                    MessageViewHolder holder = new MessageViewHolder(view);
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
    private void ValidateProductData() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat Time = new SimpleDateFormat("HH:mm");
        TimeSms = Time.format(calendar.getTime());
        Bundle arguments = getIntent().getExtras();

        Role = arguments.get("role").toString();
        Pname = productName.getText().toString();
        Description = TimeSms;

        if (Pname.equals(""))
        {
            Toast.makeText(Message.this, "Введите сообщение", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
        else
        {
            StoreProductInformation();
        }


    }

    private void StoreProductInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


        SaveProductInfoToDatabase();
    }

    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", productRandomKey);
        productMap.put("description", Description);
        productMap.put("price", Role);
        productMap.put("pname", Pname);
        //productMap.put("timesms", TimeSms);



        ProductsRefs.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(ProductsRef, Chat.class).build();

        FirebaseRecyclerAdapter<Chat, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<Chat, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull MessageViewHolder holder, int i, @NonNull @NotNull Chat model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText( model.getPrice());


            }

            @NonNull
            @NotNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_items_layout, parent, false);
                MessageViewHolder holder = new MessageViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }





    private void init() {
        productName = findViewById(R.id.product_namez);
        addNewProductButton = findViewById(R.id.btn_add_new_productz);
        ProductsRefs = FirebaseDatabase.getInstance().getReference().child("Chat");
        loadingBar = new ProgressDialog(this);
    }
}