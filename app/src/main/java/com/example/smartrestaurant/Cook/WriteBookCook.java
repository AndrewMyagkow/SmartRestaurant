package com.example.smartrestaurant.Cook;

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
import com.example.smartrestaurant.Model.WriteBook;
import com.example.smartrestaurant.R;
import com.example.smartrestaurant.ViewHolder.WriteBookAdminViewHolder;
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

public class WriteBookCook extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back;
    DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    private String  Description, Price, Pname, saveCurrentDate, saveCurrentTime, productRandomKey,TimeWrite,DateWrite;
    private EditText productName;
    private ImageView addNewProductButton;
    private DatabaseReference ProductsRefs;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writebookadmin);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("WriteBookCook");
        recyclerView = findViewById(R.id.recycler_writebook);
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
        back = findViewById(R.id.back_writebook);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteBookCook.this, CookActivity.class);
                startActivity(intent);
            }
        });


        FirebaseRecyclerOptions<WriteBook> options = new FirebaseRecyclerOptions.Builder<WriteBook>()
                .setQuery(ProductsRef, WriteBook.class).build();

        FirebaseRecyclerAdapter<WriteBook, WriteBookAdminViewHolder> adapter = new FirebaseRecyclerAdapter<WriteBook, WriteBookAdminViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull WriteBookAdminViewHolder holder, int i, @NonNull @NotNull WriteBook model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());


            }

            @NonNull
            @NotNull
            @Override
            public WriteBookAdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.writebookadmin_items_layout, parent, false);
                WriteBookAdminViewHolder holder = new WriteBookAdminViewHolder(view);
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
        SimpleDateFormat Time = new SimpleDateFormat("HH:mm ");
        TimeWrite = Time.format(calendar.getTime());
        SimpleDateFormat Date = new SimpleDateFormat("dd.MM.yyyy");
        DateWrite = Date.format(calendar.getTime());



        Pname = productName.getText().toString();
        Description = TimeWrite+DateWrite;

        if (Pname.equals(""))
        {
            Toast.makeText(WriteBookCook.this, "Введите заметку", Toast.LENGTH_SHORT).show();
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
        productMap.put("pname", Pname);



        ProductsRefs.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        FirebaseRecyclerOptions<WriteBook> options = new FirebaseRecyclerOptions.Builder<WriteBook>()
                .setQuery(ProductsRef, WriteBook.class).build();

        FirebaseRecyclerAdapter<WriteBook, WriteBookAdminViewHolder> adapter = new FirebaseRecyclerAdapter<WriteBook, WriteBookAdminViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull WriteBookAdminViewHolder holder, int i, @NonNull @NotNull WriteBook model) {
                holder.txtProductName.setText(model.getPname());
                holder.txtProductDescription.setText(model.getDescription());


            }

            @NonNull
            @NotNull
            @Override
            public WriteBookAdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.writebookadmin_items_layout, parent, false);
                WriteBookAdminViewHolder holder = new WriteBookAdminViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }





    private void init() {
        productName = findViewById(R.id.write_text);
        addNewProductButton = findViewById(R.id.add_new_text);
        ProductsRefs = FirebaseDatabase.getInstance().getReference().child("WriteBookCook");
        loadingBar = new ProgressDialog(this);
    }
}