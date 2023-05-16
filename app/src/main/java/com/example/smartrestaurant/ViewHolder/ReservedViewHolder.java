/*package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerReserved;
import com.example.smartrestaurant.R;

public class ReservedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

{
    public RelativeLayout txtrelative;
    public TextView txtNameGuest, txtDate, txtMounth, txtYear,txtClock,txtMinuts,txtKolvoGuest,txtDateText,txtTiming;
    public ItemClickListenerReserved listner;
    public ImageView delet;


    public ReservedViewHolder(View itemView)
    {
        super(itemView);


        txtNameGuest = itemView.findViewById(R.id.namereservedguest);
        txtDate = itemView.findViewById(R.id.date);
        txtMounth = itemView.findViewById(R.id.mounth);
        txtYear = itemView.findViewById(R.id.year);
        txtClock = itemView.findViewById(R.id.clock);
        txtMinuts = itemView.findViewById(R.id.minuts);
        txtKolvoGuest = itemView.findViewById(R.id.kolvoguest);
        txtDateText = itemView.findViewById(R.id.date_text);
        txtTiming =itemView.findViewById(R.id.timing);
        txtrelative = itemView.findViewById(R.id.relativereserved);
        delet = itemView.findViewById(R.id.delet_reserved);



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
*/