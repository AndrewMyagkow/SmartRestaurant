package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerReserved;
import com.example.smartrestaurant.R;

public class InfoAdminViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

{
    public TextView txtNameGuest, txtDateId, txtTimeStatus, txtKolvoGuestNumTab, txtTextName,txtTextDateId,txtTextTimeStatus,txtKolvoNumTab;
    public ItemClickListenerReserved listner;


    public InfoAdminViewHolder(View itemView)
    {
        super(itemView);


        txtNameGuest = itemView.findViewById(R.id.FIO);
        txtDateId = itemView.findViewById(R.id.date_and_id);
        txtTimeStatus = itemView.findViewById(R.id.time_and_status);
        txtKolvoGuestNumTab = itemView.findViewById(R.id.kolvoguest_and_numtable);
        txtTextName = itemView.findViewById(R.id.text_FIO);
        txtTextDateId = itemView.findViewById(R.id.text_date_and_id);
        txtTextTimeStatus = itemView.findViewById(R.id.text_time_and_status);
        txtKolvoNumTab = itemView.findViewById(R.id.text_kolvoguest_and_numtable);




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
