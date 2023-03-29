package com.example.smartrestaurant.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrestaurant.Interface.ItemClickListener;
import com.example.smartrestaurant.Interface.ItemClickListenerFeedback;
import com.example.smartrestaurant.R;

public class FeedbackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductPrimer;
    public ImageView imageView;
    public ItemClickListenerFeedback listner;


    public FeedbackViewHolder(View itemView)
    {
        super(itemView);


        txtProductName = itemView.findViewById(R.id.name_guest);
        txtProductDescription = itemView.findViewById(R.id.age_guest);
        txtProductPrice = itemView.findViewById(R.id.feedback);

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
