/*package com.example.smartrestaurant.ViewHolder;

import static androidx.core.content.ContextCompat.startActivity;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Entry.MainActivity;
import com.example.smartrestaurant.Guest.GuestActivity;
import com.example.smartrestaurant.Guest.GuestZalobiBook;
import com.example.smartrestaurant.Guest.Meny;
import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductPrimer,txtProductCategory;
    public ImageView imageView;
    public ItemClickListener listner;
    private Context context;


    public ProductViewHolder(View itemView)
    {
        super(itemView);


        imageView = itemView.findViewById(R.id.product_image);
        txtProductName = itemView.findViewById(R.id.product_name);
        txtProductDescription = itemView.findViewById(R.id.product_description);
        txtProductPrice = itemView.findViewById(R.id.product_price);
        txtProductCategory = itemView.findViewById(R.id.add_category);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                Intent adminIntent = new Intent(context, AdminActivity.class);
                context.startActivity(adminIntent);
            }
        });
    }




    public void setItemClickListner(ItemClickListener listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);

    }
}
*/