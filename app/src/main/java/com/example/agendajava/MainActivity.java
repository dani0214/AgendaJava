package com.example.agendajava;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    AdminSQL conexion;
    TextView textCodigo;
    TextView textNombre;
    TextView textApellidos;
    TextView textTLF;
    TextView textEmail;

    Spinner grupo;

    Button btBuscar;
    Button btBusqAvnz;
    Button btBaja;
    Button btGuardar;
    Button btGestionGrupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion=new AdminSQL(this, "agenda", null, 1);
        textCodigo=findViewById(R.id.textID);
        textNombre=findViewById(R.id.textNombre);
        textApellidos=findViewById(R.id.textApellidos);
        textTLF=findViewById(R.id.textTLF);
        textEmail=findViewById(R.id.textEmail);

        grupo=findViewById(R.id.spinnerGrupo);

        btBuscar=findViewById(R.id.btBuscar);
        btBusqAvnz=findViewById(R.id.btBUSQ_AVNZ);
        btBaja=findViewById(R.id.btBaja);
        btGuardar=findViewById(R.id.btGuardar);
        btGestionGrupos=findViewById(R.id.btGestionGrupos);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                if(!validarForm()){
                    CharSequence text = "Contacto Guardado con exito";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    CharSequence text = "Error al crear el contacto";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityContactos();
            }
        });

        btGestionGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityGestionGrupos();
            }
        });


    }

    public void ActivityContactos(){
        Intent listaContactos= new Intent(this, ListaContactos.class);
        startActivity(listaContactos);
    }

    public void ActivityGestionGrupos(){
        Intent gestionGrupos= new Intent(this, GestionGrupos.class);
        startActivity(gestionGrupos);
    }

    public boolean validarForm(){
        boolean hayError=false;

        Pattern regexTlf=Pattern.compile("^(0034|\\+34)?(\\d\\d\\d)-? ?(\\d\\d)-? ?(\\d)-? ?(\\d)-? ?(\\d\\d)$");
        Matcher matcherTLF=regexTlf.matcher(textTLF.getText().toString());;
        Pattern regexEmail=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherEmail=regexEmail.matcher(textEmail.getText().toString());

        TextView errorNombre=findViewById(R.id.errorNombre);
        errorNombre.setVisibility(View.INVISIBLE);
        TextView errorApellidos=findViewById(R.id.errorApellidos);
        errorApellidos.setVisibility(View.INVISIBLE);
        TextView errorID=findViewById(R.id.errorID);
        errorID.setVisibility(View.INVISIBLE);
        TextView errorTLF=findViewById(R.id.errorTLF);
        errorTLF.setVisibility(View.INVISIBLE);
        TextView errorEmail=findViewById(R.id.errorEmail);
        errorEmail.setVisibility(View.INVISIBLE);

        if(textNombre.getText().toString().equals("")){
            errorNombre.setText("Hay un error en el nombre, pruebe de nuevo");
            errorNombre.setVisibility(View.VISIBLE);
            hayError=true;
        }
        if(textApellidos.getText().toString().equals("")){
            errorApellidos.setText("Hay un error en los apellidos, pruebe de nuevo");
            errorApellidos.setVisibility(View.VISIBLE);
            hayError=true;
        }
        if(textEmail.getText().toString().equals("") || !matcherEmail.find()){
            errorEmail.setText("Hay un error en el email, pruebe de nuevo");
            errorEmail.setVisibility(View.VISIBLE);
            hayError=true;
        }
        if(textTLF.getText().toString().equals("") || !matcherTLF.find()){
            errorTLF.setText("Hay un error en el telefono, pruebe de nuevo");
            errorTLF.setVisibility(View.VISIBLE);
            hayError=true;
        }
        if(textCodigo.getText().toString().equals("")){
            errorID.setText("Hay un error en el codigo, pruebe de nuevo");
            errorID.setVisibility(View.VISIBLE);
            hayError=true;
        }
        return hayError;
    }

}