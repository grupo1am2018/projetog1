package com.example.apaul.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlantelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlantelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    float dX, dY;
    private GestureDetector gestureDetector;
    public ArrayList<ImageView> jogadores;
    ArrayList<FormacaoPlantel> formacao;
    RelativeLayout relativeLayout;
    View view;

    private OnFragmentInteractionListener mListener;

    public PlantelFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlantelFragment newInstance() {
        PlantelFragment fragment = new PlantelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
                //saveData();
                return true;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Definir variaveis
        view = inflater.inflate(R.layout.fragment_plantel, container, false);
        jogadores = new ArrayList<>();
        formacao = new ArrayList<>();
        relativeLayout = (RelativeLayout) view.findViewById(R.id.llcampo);
        RecyclerView listaJogadores = (RecyclerView) view.findViewById(R.id.plantelRecyclerView);
        String[] nomeJogador = new String[11];

        for(int i = 0; i < nomeJogador.length; i++) {
            nomeJogador[i] = "teste" + Integer.toString(i+1);
        }
        //Cria uma variavel da classe adaptador
        PlantelAdapter adapter = new PlantelAdapter(nomeJogador, getActivity());

        //Define o tipo de layout, neste caso grelha
        listaJogadores.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        listaJogadores.setNestedScrollingEnabled(false);
        listaJogadores.setAdapter(adapter);
       // gestureDetector = new GestureDetector(getActivity(), new PlantelFragment.SingleTapConfirm());
        //formacao.add(new FormacaoPlantel(2F, 2F, 1));
        //loadData();
        return view;
    }
    /*
    private void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("plantel", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("plantel", null);
        Type type = new TypeToken<ArrayList<FormacaoPlantel>>() {}.getType();
        formacao = gson.fromJson(json, type);
        formacao.add(new FormacaoPlantel(150F, 150F, 1));
        if(!(formacao == null)){
            for(int i = 0; i < formacao.size(); i++){
                criarJogador(i, "ssd", "sds", formacao.get(i).x, formacao.get(i).y, getActivity());
            }
        }
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("plantel", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(formacao);
        editor.putString("plantel", json);
        editor.apply();
    }

    void criarJogador (int index, String nome, String posicao, float x, float y, Context context){

        jogadores.add(new ImageView(context));
        jogadores.get(index).setImageResource(R.drawable.defesa);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.llcampo);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        int devicewidth = (int) Math.round(displaymetrics.widthPixels * 0.10);

        //if you need 4-5-6 anything fix imageview in height
        int deviceheight = (int) Math.round(displaymetrics.heightPixels * 0.10);

        relativeLayout.addView(jogadores.get(index));
        jogadores.get(index).getLayoutParams().width = devicewidth;
        jogadores.get(index).getLayoutParams().height = deviceheight;

        jogadores.get(index).setX(x);
        jogadores.get(index).setY(y);

        gestureDetector = new GestureDetector(getActivity(), new PlantelFragment.SingleTapConfirm());
        jogadores.get(index).setOnTouchListener(motionEvent);

           /* FormacaoPlantel novojogador = new FormacaoPlantel(jogadores.get(numJogadores).getX(),
                    jogadores.get(numJogadores).getY(), 123);

            formacao.add(numJogadores, novojogador);

    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
}
