package com.argentruck.argentruck_cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.argentruck.argentruck_cliente.http.HttpPostConnection;
import com.argentruck.argentruck_cliente.http.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestTravel extends AppCompatActivity implements HttpResponseListener {

    private EditText origen;
    private EditText destino;
    private EditText capacidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_travel);

        origen = (EditText) findViewById(R.id.origen);
        destino = (EditText) findViewById(R.id.destiny);
        capacidad = (EditText) findViewById(R.id.capacidad);
    }

    public void sugerirViaje(View v){

        HttpPostConnection httpPostConnection = new HttpPostConnection(this);
        String uri = Singletonazo.getInstance().getIp() + ":3000/users/addSuggestTravel";
        httpPostConnection.setUri(uri);

        JSONObject body = buildConfirBody();

        if(body != null){
            httpPostConnection.setBody(body);
        }

        httpPostConnection.execute();
    }

    private JSONObject buildConfirBody(){
        String origen = this.origen.getText().toString();
        String destino = this.destino.getText().toString();
        int capacidad = Integer.parseInt(this.capacidad.getText().toString());

        try {
            JSONObject data = new JSONObject();
            data.put("email", Singletonazo.getInstance().getEmail());
            data.put("origin", origen);
            data.put("destiny", destino);
            data.put("capacity", capacidad);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", data);

            return jsonObject;
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        finish();
    }

    @Override
    public void handleHttpError(String error) {
        finish();
    }
}
