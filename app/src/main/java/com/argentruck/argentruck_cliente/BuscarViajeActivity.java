package com.argentruck.argentruck_cliente;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A login screen that offers login via email/password.
 */
public class BuscarViajeActivity extends AppCompatActivity {

    private EditText origen;
    private EditText destino;
    private EditText fecha;
    private EditText capacidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_viaje);
        origen = (EditText) findViewById(R.id.origen);
        destino = (EditText) findViewById(R.id.destino);
        fecha = (EditText) findViewById(R.id.fecha);
        capacidad = (EditText) findViewById(R.id.espacio);
    }

    public void buscarViaje(View view) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String o = origen.getText().toString();
        String d = destino.getText().toString();
        String f = fecha.getText().toString();
        int eMin = Integer.parseInt(capacidad.getText().toString());

        Intent elegirViajeActivity = new Intent(BuscarViajeActivity.this, ElegirViajeActivity.class);
        elegirViajeActivity.putExtra("origen", o);
        elegirViajeActivity.putExtra("destino", d);
        elegirViajeActivity.putExtra("fecha", f);
        elegirViajeActivity.putExtra("cap", eMin);

        startActivity(elegirViajeActivity);
    }
}

