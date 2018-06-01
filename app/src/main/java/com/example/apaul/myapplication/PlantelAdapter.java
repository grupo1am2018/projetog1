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

import com.squareup.picasso.Picasso;

/**
 * Created by apaul on 25/05/2018.
 */

public class PlantelAdapter extends RecyclerView.Adapter<PlantelHolder> {
    String[] nomeJogador;
    Bitmap[] fotoJogador;
    float dX, dY;
    Context context;
    int devicewidth;
    int deviceheight;
    private GestureDetector gestureDetector;

    public PlantelAdapter(String[] nomeJogador, Context context) {
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
    public PlantelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.camisola, parent, false);
        PlantelHolder viewHolder = new PlantelHolder(v);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        devicewidth =  (int) Math.round(displaymetrics.widthPixels * 0.10);

        //if you need 4-5-6 anything fix imageview in height
        deviceheight = (int) Math.round(displaymetrics.heightPixels * 0.10);

        v.getLayoutParams().width = devicewidth;

        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
        v.getLayoutParams().height = deviceheight;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(PlantelHolder holder, final int position) {

        gestureDetector = new GestureDetector(context, new PlantelAdapter.SingleTapConfirm());
        int width = (int) Math.round(devicewidth * 0.7);
        int height = (int) (deviceheight * 0.7);
        Picasso.with(context).load(R.drawable.defesa).fit().into(holder.camisolaImage);
        holder.camisolaRL.setOnTouchListener(motionEvent);
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
