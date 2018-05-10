package com.example.apaul.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apaul on 01/05/2018.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{

    public ImageView logo;
    public TextView name;

    public MyViewHolder(View itemView) {
        super(itemView);
        logo = itemView.findViewById(R.id.fotoJogador);
        name = itemView.findViewById(R.id.nomeJogador);
    }
}