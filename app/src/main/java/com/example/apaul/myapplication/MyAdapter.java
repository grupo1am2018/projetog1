package com.example.apaul.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by apaul on 01/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    String[] nomeJogador;
    Bitmap[] fotoJogador;
    float dX, dY;
    Context context;
    int devicewidth;
    int deviceheight;
    private GestureDetector gestureDetector;

    public MyAdapter(String[] nomeJogador, Context context) {
        this.nomeJogador = nomeJogador;
        //this.fotoJogador = fotoJogador;
        this.context = context;
    }

    View.OnTouchListener motionEvent = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {
                //criarJogador(jogadores.size(),"ssd", "sds", 0.0F, 0.0F, getActivity());
                return true;
            } else {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        v.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        }
    };
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jogador, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        devicewidth = displaymetrics.widthPixels / 3;

        //if you need 4-5-6 anything fix imageview in height
        deviceheight = displaymetrics.heightPixels / 4;

        v.getLayoutParams().width = devicewidth;

        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
        v.getLayoutParams().height = deviceheight;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        gestureDetector = new GestureDetector(context, new SingleTapConfirm());
        holder.jogadorrl.setOnTouchListener(motionEvent);
        Picasso.with(context).load("https://as01.epimg.net/img/comunes/fotos/fichas/deportistas/a/and/large/31985.png").fit().into(holder.logo);

        holder.name.setText(nomeJogador[position]);
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, Jogador.class);
                context.startActivity(myIntent);
            }
        });
    }
    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
    @Override
    public int getItemCount() {
        return nomeJogador.length;
    }
}