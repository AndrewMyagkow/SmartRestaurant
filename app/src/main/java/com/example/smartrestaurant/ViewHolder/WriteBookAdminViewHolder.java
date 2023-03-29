package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerWriteBookAdmin;
import com.example.smartrestaurant.R;

public class WriteBookAdminViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductPrimer;
    public ItemClickListenerWriteBookAdmin listnerzs;


    public WriteBookAdminViewHolder(View itemView)
    {
        super(itemView);


        txtProductName = itemView.findViewById(R.id.write_text);
        txtProductDescription = itemView.findViewById(R.id.time);

    }

    public void setItemClickListner(ItemClickListenerWriteBookAdmin listnerzs)
    {
        this.listnerzs = listnerzs;
    }

    @Override
    public void onClick(View view)
    {
        listnerzs.onClick(view, getAdapterPosition(), false);
    }
}
