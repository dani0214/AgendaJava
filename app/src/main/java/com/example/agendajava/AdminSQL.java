package com.example.agendajava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQL extends SQLiteOpenHelper {


    public AdminSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase agendaDB) {
        agendaDB.execSQL("CREATE TABLE IF NOT EXISTS grupos(id int PRIMARY KEY, nombre TEXT)");
        agendaDB.execSQL("CREATE TABLE IF NOT EXISTS contactos(id int PRIMARY KEY, nombre TEXT, apellidos TEXT, tlf TEXT, email TEXT, grupo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void comprobarID(){

    }

    public boolean comprobarGrupo(String grupo){
        return true;
    }

    public void crearContacto(){

    }

    public void crearGrupo(){

    }

    public void buscarContacto(){

    }

    public void buscarGrupos(){

    }

    public void eliminarContacto(){

    }

    public void eliminarGrupo(){

    }

}
