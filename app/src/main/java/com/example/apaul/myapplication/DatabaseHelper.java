package com.example.apaul.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apaul on 5/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Versão da Base de Dados
    private static final int DATABASE_VERSION = 1;

    // Nome da Base de dados
    private static final String DATABASE_NAME = "projectoADDJN.db";

    public static final String COLUMN_PLANTEL_CODJOGADOR = "codJogador";
    public static final String COLUMN_PLANTEL_X = "x";
    public static final String COLUMN_PLANTEL_Y = "y";
    public static final String[] COLUMNS_PLANTEL = {COLUMN_PLANTEL_CODJOGADOR, COLUMN_PLANTEL_X, COLUMN_PLANTEL_Y};

    // Construtor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String jogadorPlantel = "CREATE TABLE FormacaoPlantel"
                + "(codJogador integer primary key,"
                + " x real,"
                + " y real)";
        sqLiteDatabase.execSQL(jogadorPlantel);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void erase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}