package com.example.agendajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class GestionGrupos extends AppCompatActivity {

    AdminSQL conexion;
    SQLiteDatabase BaseDatos;

    TextView nombreGrupoText;
    TextView listaGrupos;

    Button btVolverGestion;
    Button btGuardarGrupo;
    Button btEliminarGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_grupos);

        conexion=new AdminSQL(this, "agenda", null, 1);
        BaseDatos = conexion.getWritableDatabase();

        nombreGrupoText=findViewById(R.id.nombreGrupoText);
        listaGrupos=findViewById(R.id.listaGrupos);

        btGuardarGrupo=findViewById(R.id.btGuardarGrupo);
        btEliminarGrupo=findViewById(R.id.btEliminarGrupo);
        btVolverGestion=findViewById(R.id.btVolverGestionGrupo);

        btVolverGestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityVolverGestion();
            }
        });

        btGuardarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                if(!comprobarNombreGrupo("guardar")){
                    CharSequence text = "Grupo creado con exito";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    CharSequence text = "Error al crear el grupo";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        btEliminarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                if(!comprobarNombreGrupo("eliminar")){
                    CharSequence text = "Grupo eliminado con exito";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    CharSequence text = "Error al eliminar el grupo";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }

    public void ActivityVolverGestion(){
        Intent main= new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public boolean comprobarNombreGrupo(String accion){
        boolean error=false;
        ContentValues registro= new ContentValues();
        TextView errorNombreGrupo=findViewById(R.id.errorNombreGrupo);
        errorNombreGrupo.setVisibility(View.INVISIBLE);
        String grupo=nombreGrupoText.getText().toString();
        if(nombreGrupoText.getText().toString().equals("")){
            errorNombreGrupo.setText("El campo no puede estar vacio, intentelo de nuevo");
            errorNombreGrupo.setVisibility(View.VISIBLE);
            error=true;
        } if (!error){
            switch (accion){
                case "guardar":
                    if(!conexion.comprobarGrupo(grupo)){
                        registro.put("nombre", nombreGrupoText.getText().toString());
                        BaseDatos.insert("grupos", null, registro);
                    } else {
                        errorNombreGrupo.setText("El grupo introducido ya existe, intentelo de nuevo");
                        errorNombreGrupo.setVisibility(View.VISIBLE);
                        error = true;
                    }
                    break;
                case "eliminar":
                    if(conexion.comprobarGrupo(nombreGrupoText.getText().toString())){
                        registro.put("nombre", nombreGrupoText.getText().toString());
                        int cantidad=BaseDatos.delete("grupos", "nombre="+grupo,null);
                    } else {
                        errorNombreGrupo.setText("El grupo introducido no existe, intentelo de nuevo");
                        errorNombreGrupo.setVisibility(View.VISIBLE);
                        error= true;
                    }
                    break;
            }
        }

        return error;
    }

}