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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Admin.Reserved.AddReservedActivity;
import com.example.smartrestaurant.Admin.Reserved.ReservedActivity;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Model.Baskets;
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

public class Basket extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView back;
    private EditText komment;
    private Button zakaz;
    private String AdminInfo ="Выполняется";
    private String Symma,Kolvo,Accomplishment,saveCurrentDate, saveCurrentTime, productRandomKey, ITOG,Komment,barman;
    private String allZakaz = "";
    private String allZakazbar = "";
    private int sym,itog,kol;
    static DatabaseReference ProductsRef;
    private DatabaseReference AccomplishmentRef;
    private DatabaseReference PayRef;
    private DatabaseReference AccomplishmentRefBar;
    private DatabaseReference InfoRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child(SettingsGuest.numtab);
        AccomplishmentRef = FirebaseDatabase.getInstance().getReference().child("Accomplishment");
        PayRef = FirebaseDatabase.getInstance().getReference().child("Pay");
        AccomplishmentRefBar = FirebaseDatabase.getInstance().getReference().child("AccomplishmentBar");
        InfoRef = FirebaseDatabase.getInstance().getReference().child("InfoAdmin");
        loadingBar = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recycler_basket);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
        back = findViewById(R.id.back_basket);
        zakaz = findViewById(R.id.zakaz);
        komment = findViewById(R.id.komment);
        TextView itogsym = findViewById(R.id.itog);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this, GuestActivity.class);
                startActivity(intent);
            }
        });
        zakaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Komment = komment.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());
                SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
                saveCurrentTime = currentTime.format(calendar.getTime());
                productRandomKey = "A"+saveCurrentDate + saveCurrentTime;

                HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("pid", productRandomKey);
                productMap.put("date", saveCurrentDate);
                productMap.put("time", saveCurrentTime);
                productMap.put("dishes", allZakaz);
                productMap.put("symma", ITOG);
                productMap.put("komment", Komment);
                productMap.put("barman", allZakazbar);
                productMap.put("table", SettingsGuest.numtab);
                productMap.put("admin", AdminInfo);

              if((!allZakaz.equals(""))&&(!allZakazbar.equals(""))) {
                  AccomplishmentRef.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if (task.isSuccessful()) {
                                      Toast.makeText(Basket.this, "Заказ принят", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();
                                      ProductsRef = FirebaseDatabase.getInstance().getReference();
                                      ProductsRef.child(SettingsGuest.numtab).setValue(null);
                                      Intent loginIntent = new Intent(Basket.this, GuestActivity.class);
                                      startActivity(loginIntent);
                                  }
                              }
                          });

                  AccomplishmentRefBar.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                              }
                          });
                  InfoRef.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                              }
                          });
              }
              else if ((!allZakaz.equals(""))&&(allZakazbar.equals("")))
              {
                  AccomplishmentRef.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if (task.isSuccessful()) {
                                      Toast.makeText(Basket.this, "Заказ принят", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();

                                      ProductsRef = FirebaseDatabase.getInstance().getReference();
                                      ProductsRef.child(SettingsGuest.numtab).setValue(null);
                                      Intent loginIntent = new Intent(Basket.this, GuestActivity.class);
                                      startActivity(loginIntent);
                                  }
                              }
                          });
                  InfoRef.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                              }
                          });
              }
              else if ((allZakaz.equals(""))&&(!allZakazbar.equals("")))
              {
                  AccomplishmentRefBar.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if (task.isSuccessful()) {
                                      Toast.makeText(Basket.this, "Заказ принят", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();

                                      ProductsRef = FirebaseDatabase.getInstance().getReference();
                                      ProductsRef.child(SettingsGuest.numtab).setValue(null);
                                      Intent loginIntent = new Intent(Basket.this, GuestActivity.class);
                                      startActivity(loginIntent);
                                  }
                              }
                          });
                  InfoRef.child(productRandomKey).updateChildren(productMap)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                              }
                          });
              }
                PayRef.child(productRandomKey).updateChildren(productMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
            }
        });

        FirebaseRecyclerOptions<Baskets> options = new FirebaseRecyclerOptions.Builder<Baskets>()
                .setQuery(ProductsRef, Baskets.class).build();

        FirebaseRecyclerAdapter<Baskets, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Baskets, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Baskets model) {
                holder.txtProductName.setText(model.getName());
                holder.txtProductPrice.setText( model.getPrice());
                holder.txtPid.setText(model.getPid());
                holder.txtColvo.setText(model.getValuekolvo()+" шт");
               if ((model.getCategory().equals("Напитки"))||(model.getCategory().equals("Винная Карта")))
               {
                   barman = model.getName()+" "+model.getValuekolvo()+" шт ";
                   allZakazbar = barman+allZakazbar;
               }
               else
               {
                   Accomplishment = model.getName()+" "+model.getValuekolvo()+" шт ";
                   allZakaz = Accomplishment+allZakaz;
               }

                Kolvo = model.getValuekolvo();
                Symma = model.getPrice();
                String[] parts = Symma.split(" ");
                String res = parts[0];
                sym = Integer.parseInt (res);
                kol = Integer.parseInt(Kolvo);
                itog = (sym*kol)+itog;
                 ITOG = Integer.toString(itog);
                itogsym.setText("Итог: "+ITOG);
            }
            @NonNull
            @NotNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout_basket, parent, false);
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
        public TextView txtProductName, txtProductPrice,txtColvo,txtPid;
        public ItemClickListener listner;
        public ImageView delet;
        public ProductViewHolder(View itemView) {
            super(itemView);


            txtProductName = itemView.findViewById(R.id.product_name_basket);
            txtProductPrice = itemView.findViewById(R.id.product_price_basket);
            txtColvo = itemView.findViewById(R.id.product_colvo_basket);
            txtPid = itemView.findViewById(R.id.pid_basket);
            delet = itemView.findViewById(R.id.delet_basket);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String pid = txtPid.getText().toString();
                    ProductsRef = FirebaseDatabase.getInstance().getReference();
                    ProductsRef.child(SettingsGuest.numtab+"/"+pid).setValue(null);
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