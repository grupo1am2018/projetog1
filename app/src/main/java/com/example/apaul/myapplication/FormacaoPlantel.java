package com.example.apaul.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by apaul on 25/05/2018.
 */

class FormacaoPlantel {
    float x, y;
    int codJogador;

    public FormacaoPlantel(){
    }

    public FormacaoPlantel(int codJogador, float x, float y){
        this.x = x;
        this.y = y;
        this.codJogador = codJogador;
    }

    public float getjogadorX() {
        return x;
    }

    public void setjogadorX(float x) {
        this.x = x;
    }

    public float getjogadorY() {
        return y;
    }

    public void setjogadorY(float y) {
        this.y = y;
    }

    public int getCodJogador() {
        return codJogador;
    }

    public void setCodJogador(int codJogador) {
        this.codJogador = codJogador;
    }
    public static FormacaoPlantel insertJogadorPlantel(float x, float y, int codJogador, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues jogadorFormacao = new ContentValues();

        jogadorFormacao.put("codJogador", codJogador);
        jogadorFormacao.put("x", x);
        jogadorFormacao.put("y", y);

        long result = database.insert("FormacaoPlantel", null, jogadorFormacao);

        Cursor cursor = database.query("FormacaoPlantel", DatabaseHelper.COLUMNS_PLANTEL, "codJogador" + " = "  + result, null, null, null, null);
        cursor.moveToFirst();
        FormacaoPlantel jogador = novoJogador(cursor);
        cursor.close();
        database.close();
        return jogador;
    }

    public static FormacaoPlantel novoJogador(Cursor cursor) {
        FormacaoPlantel jogador = new FormacaoPlantel();
        jogador.setCodJogador(cursor.getInt(0));
        jogador.setjogadorX(cursor.getFloat(1));
        jogador.setjogadorX(cursor.getFloat(2));
        return jogador;
    }

    public static void UpdatePosicaoJogador(float x, float y, int codJogador, Context context){
        ContentValues jogador = new ContentValues();
        jogador.put("codJogador", codJogador);
        jogador.put("x", x);
        jogador.put("y", y);
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("FormacaoPlantel", jogador, "codJogador = " + codJogador,  null);
        db.close();
    }
    public static ArrayList<FormacaoPlantel> getFormacaoPlantel(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<FormacaoPlantel> aFormacao = new ArrayList<>();
        Cursor res = db.rawQuery("select * from formacaoplantel", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            aFormacao.add(new FormacaoPlantel(res.getInt(0), res.getFloat(1), res.getFloat(2)));
            res.moveToNext();
        }
        res.close();
        db.close();
        return aFormacao;
    }
}
