package com.example.apaul.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by apaul on 25/05/2018.
 */

public class PlantelHolder extends RecyclerView.ViewHolder{

    public ImageView camisolaImage;
    public RelativeLayout camisolaRL;

    public PlantelHolder(View itemView) {
        super(itemView);
        camisolaImage = itemView.findViewById(R.id.plantelCamisola);
        camisolaRL = itemView.findViewById(R.id.camisolarl);
    }
}