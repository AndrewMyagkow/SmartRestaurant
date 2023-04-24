package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerReserved;
import com.example.smartrestaurant.R;

public class ReservedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

{
    public RelativeLayout txtrelative;
    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductPrimer,txtClock,txtMinuts,txtKolvoGuest,txtDateText,txtTiming;
    public ItemClickListenerReserved listner;

    public ReservedViewHolder(View itemView)
    {
        super(itemView);


        txtProductName = itemView.findViewById(R.id.namereservedguest);
        txtProductDescription = itemView.findViewById(R.id.date);
        txtProductPrice = itemView.findViewById(R.id.mounth);
        txtProductPrimer = itemView.findViewById(R.id.year);
        txtClock = itemView.findViewById(R.id.clock);
        txtMinuts = itemView.findViewById(R.id.minuts);
        txtKolvoGuest = itemView.findViewById(R.id.kolvoguest);
        txtDateText = itemView.findViewById(R.id.date_text);
        txtTiming =itemView.findViewById(R.id.timing);
        txtrelative = itemView.findViewById(R.id.relativereserved);



    }

    public void setItemClickListner(ItemClickListenerReserved listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
