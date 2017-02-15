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
public class BuscarViajeActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    //private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    /*
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    */
    private String email;
    private EditText origen;
    private EditText destino;
    private EditText fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_viaje);
        origen = (EditText) findViewById(R.id.origen);
        destino = (EditText) findViewById(R.id.destino);
        fecha = (EditText) findViewById(R.id.fecha);
    }

    public void buscarViaje(View view) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String o = origen.getText().toString();
        String d = destino.getText().toString();
        String f = fecha.getText().toString();
        /*try {
            cal.setTime(df.parse(fecha.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.get(Calendar.DAY_OF_MONTH);
        */
        // Aca podria haber una pantalla intermedia con un mapa y botones de aceptar/atras
        Intent elegirViajeActivity = new Intent(BuscarViajeActivity.this, ElegirViajeActivity.class);
        elegirViajeActivity.putExtra("origen", o);
        elegirViajeActivity.putExtra("destino", d);
        elegirViajeActivity.putExtra("fecha", f);

        startActivity(elegirViajeActivity);
        finish();
        // aca hacemos el request al server
        // obtenemos respuesta
        // y pasamos a la view con los viajes!
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

}

