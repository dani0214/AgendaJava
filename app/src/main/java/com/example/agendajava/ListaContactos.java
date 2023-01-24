package com.example.agendajava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListaContactos extends AppCompatActivity {

    AdminSQL conexion;
    SQLiteDatabase BaseDatos;
    TextView contactoText;
    TextView listaContacto;
    Button btBuscarContacto;
    Button btVolverListaContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);

        conexion=new AdminSQL(this, "agenda", null, 1);
        BaseDatos = conexion.getWritableDatabase();

        contactoText=findViewById(R.id.contactoText);
        listaContacto=findViewById(R.id.listaContactoText);
        btBuscarContacto=findViewById(R.id.btBuscaContacto);
        btVolverListaContacto=findViewById(R.id.btVolverListaContacto);

        btVolverListaContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityVolverContactos();
            }
        });
        btBuscarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarContacto();
            }
        });

    }

    public void ActivityVolverContactos(){
        Intent main= new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void buscarContacto(){
        Context context = getApplicationContext();
        String busqueda= contactoText.getText().toString();
        if(busqueda.equals("")) {
            CharSequence text = "Busqueda realizada con exito";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}