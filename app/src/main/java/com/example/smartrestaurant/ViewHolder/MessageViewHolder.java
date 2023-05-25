package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerMessage;
import com.example.smartrestaurant.R;

public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ItemClickListenerMessage listnerz;
    public MessageViewHolder(View itemView)
    {
        super(itemView);
        txtProductName = itemView.findViewById(R.id.product_namez);
        txtProductDescription = itemView.findViewById(R.id.product_descriptionz);
        txtProductPrice = itemView.findViewById(R.id.product_pricez);
    }
    public void setItemClickListner(ItemClickListenerMessage listnerz)
    {
        this.listnerz = listnerz;
    }

    @Override
    public void onClick(View view)
    {
        listnerz.onClick(view, getAdapterPosition(), false);
    }
}
