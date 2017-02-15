package com.argentruck.argentruck_cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.argentruck.argentruck_cliente.entidades.Viaje;
import com.argentruck.argentruck_cliente.http.HttpPostConnection;
import com.argentruck.argentruck_cliente.http.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmarViajeActivity extends AppCompatActivity implements HttpResponseListener {

    private TextView desde;
    private TextView hasta;
    private TextView conductor;
    private TextView fecha;
    private TextView cargaDisponible;
    private TextView cargaDeseada;

    private String viajeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_viaje);

        Viaje viaje = Singletonazo.getInstance().getCurrentShowViaje();
        viajeId = viaje.getId();
        //TextView travelDirection = (TextView) customView.findViewById(R.id.ftsi_tv_travel);
        //travelDirection.setText("Desde " + viajeActual.getOrigen() + " a " + viajeActual.getDestino());
        desde  = (TextView) findViewById(R.id.desde);
        desde.setText("Origen: " + viaje.getOrigen());
        hasta  = (TextView) findViewById(R.id.hasta);
        hasta.setText("Destino : " + viaje.getDestino());
        conductor  = (TextView) findViewById(R.id.conductor);
        conductor.setText("Conductor: " + viaje.getConductor());
        fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText("Fecha: " + viaje.getFecha());
        cargaDisponible = (TextView) findViewById(R.id.cargaDisponible);
        cargaDisponible.setText("Espacio libre :" + viaje.getEspacioLibre());
        cargaDeseada = (TextView) findViewById(R.id.cargaDeseada);

        //Viaje viajeVista = (Viaje) findViewById(R.id.viajeVista);
    }

    private JSONObject buildConfirBody(){
        try {
            JSONObject data = new JSONObject();
            data.put("id", viajeId);
            data.put("email", Singletonazo.getInstance().getEmail());
            data.put("capacity", 10);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", data);

            return jsonObject;
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public void confirmarViaje(View v){
        HttpPostConnection httpPostConnection = new HttpPostConnection(this);
        String uri = Singletonazo.getInstance().getIp() + ":3000/users/addTravel";
        httpPostConnection.setUri(uri);

        JSONObject body = buildConfirBody();

        if(body != null){
            httpPostConnection.setBody(body);
        }

        httpPostConnection.execute();
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
