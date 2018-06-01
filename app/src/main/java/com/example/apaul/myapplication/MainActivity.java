package com.example.apaul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cria a view que mostra os jogadores
        RecyclerView listaJogadores = (RecyclerView) findViewById(R.id.recyclerId);

        //Cria uma string e insere os jogadores na view
        //Aqui pode ser mudado para um array de jogadores e mandado para o construtor do adapter
        String[] nomeJogador = new String[23];

        for(int i = 0; i < nomeJogador.length; i++) {
            nomeJogador[i] = "teste" + Integer.toString(i+1);
        }

        //Cria uma variavel da classe adaptador
        MyAdapter adapter = new MyAdapter(nomeJogador, this);

        //Define o tipo de layout, neste caso grelha
        listaJogadores.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

        listaJogadores.setAdapter(adapter);


    }
}
