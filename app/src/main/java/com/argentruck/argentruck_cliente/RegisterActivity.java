package com.argentruck.argentruck_cliente;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mContrasenaView;
    private EditText mNombreView;
    private EditText mApellidoView;
    private EditText mDireccionView;
    private EditText mCBUView;
    private EditText mTelefonoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (EditText) findViewById(R.id.email);
        mContrasenaView = (EditText) findViewById(R.id.password);
        mNombreView = (EditText) findViewById(R.id.name);
        mApellidoView = (EditText) findViewById(R.id.surname);
        mDireccionView = (EditText) findViewById(R.id.address);
        mCBUView = (EditText) findViewById(R.id.cbu);
        mTelefonoView = (EditText) findViewById(R.id.telephone);

        Button mRegisterButton = (Button) findViewById(R.id.email_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidData()) {
                    register();
                }
            }
        });
    }

    private boolean checkValidData() {
        if (!(mEmailView.getText().toString().trim()).contains("@")) {
            Toast.makeText(getApplicationContext(), "Email Incorrecto", Toast.LENGTH_LONG).show();
            return false;
        }
        if ((mContrasenaView.getText().toString().trim()).length() < 8) {
            Toast.makeText(getApplicationContext(), "La contraseña es muy corta, debe tener al menos 8 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }
        if ((mCBUView.getText().toString().trim()).length()== 0) {
            Toast.makeText(getApplicationContext(), "El CBU es obligatorio", Toast.LENGTH_LONG).show();
            return false;
        }
        if ((mTelefonoView.getText().toString().trim()).length() == 0) {
            Toast.makeText(getApplicationContext(), "El telefono es obligatorio", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void register() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = getResources().getString(R.string.http_ip) + "/users/";

        final Context context = getApplicationContext();

        JSONObject jsParam = new JSONObject();
        JSONObject mRequestParams = new JSONObject();

        try {
            mRequestParams.put("email", mEmailView.getText().toString().trim());
            mRequestParams.put("password", mContrasenaView.getText().toString().trim());
            mRequestParams.put("nombre", mNombreView.getText().toString().trim());
            mRequestParams.put("apellido", mApellidoView.getText().toString().trim());
            mRequestParams.put("direccion", mDireccionView.getText().toString().trim());
            mRequestParams.put("cbu", mCBUView.getText().toString().trim());
            mRequestParams.put("telefono", mTelefonoView.getText().toString().trim());

            jsParam.put("data", mRequestParams);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Singletonazo.getInstance().setEmail(mEmailView.getText().toString().trim());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsParam, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if ((response.toString()).contains("created")) {
                    Toast.makeText(context, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show();
                    Intent intentMain = new Intent(RegisterActivity.this, MainActivity.class );
                    intentMain.putExtra("email", mEmailView.getText().toString().trim());
                    startActivity(intentMain);
                } else {
                    Toast.makeText(context, "Error: Alguno de los datos ingresados es incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: Problema con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }
}
