package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListenerFeedback;
import com.example.smartrestaurant.R;

public class FeedbackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtName, txtTime, txtFeedback;
    public ItemClickListenerFeedback listner;


    public FeedbackViewHolder(View itemView)
    {
        super(itemView);


        txtName = itemView.findViewById(R.id.name_guest_zalobi);
        txtTime = itemView.findViewById(R.id.time_guest_zalobi);
        txtFeedback = itemView.findViewById(R.id.feedback);

    }

    public void setItemClickListner(ItemClickListenerFeedback listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
