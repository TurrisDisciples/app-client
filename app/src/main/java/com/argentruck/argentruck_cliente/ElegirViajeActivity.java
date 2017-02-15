package com.argentruck.argentruck_cliente;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.argentruck.argentruck_cliente.entidades.Viaje;
import com.argentruck.argentruck_cliente.http.HttpGetConnection;
import com.argentruck.argentruck_cliente.http.HttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ElegirViajeActivity  extends AppCompatActivity implements HttpResponseListener, AdapterView.OnItemClickListener {

    ElegirViajeListAdapter evla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_viaje);

        ListView viajesVista = (ListView) findViewById(R.id.aev_lv_travellist);
        evla = new ElegirViajeListAdapter(this);
        viajesVista.setOnItemClickListener(this);

        viajesVista.setAdapter(evla);
        HttpGetConnection httpGetConnection = new HttpGetConnection(this);
        String uri = Singletonazo.getInstance().getIp() + ":3000/users/travels";
        httpGetConnection.setUri(uri);
        httpGetConnection.execute();
    }

    public void confirmarViaje(View view) {
        //Guardar viaje en singleton.
        Intent confirmarViajeActivity = new Intent(ElegirViajeActivity.this, ConfirmarViajeActivity.class);
        startActivity(confirmarViajeActivity);
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            JSONArray subList = responseBody.getJSONArray("data");
            ArrayList<Viaje> vList = new ArrayList<>();
            for(int i = 0; i < subList.length(); i++){
                JSONObject cJson = subList.getJSONObject(i);
                String fecha = cJson.getString("date");
                String origen = cJson.getString("origin");
                String destino = cJson.getString("destiny");
                String id = cJson.getString("_id");
                int capMax = cJson.getInt("capMax");
                int capCurrent = cJson.getInt("capCurrent");
                String conductor = cJson.getString("email");
                Viaje v = new Viaje(fecha, origen, destino, capMax, capCurrent);
                v.setId(id);
                v.setConductor(conductor);
                vList.add(v);
            }

            evla.addAll(vList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleHttpError(String error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Singletonazo.getInstance().setCurrentShowViaje(evla.getItem(position));
        Intent intent = new Intent(ElegirViajeActivity.this, ConfirmarViajeActivity.class);
        startActivity(intent);
    }
}
