package com.argentruck.argentruck_cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HttpResponseListener {

    private String email;
    private ListView mTripsList;
    private TripsAdapter tripsAdapter;
    private HttpGetConnection httpGetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = getIntent().getStringExtra("email");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Button getIpButton = (Button) findViewById(R.id.get_ip);
//        getIpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getIP();
//            }
//        });


        // Instancia del ListView.
        mTripsList = (ListView) findViewById(R.id.trips_list);

        // Inicializar el adaptador con la fuente de datos.
        tripsAdapter = new TripsAdapter(this);

        //Relacionando la lista con el adaptador
        mTripsList.setAdapter(tripsAdapter);

        // Eventos
        mTripsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, ClientsActivity.class);
                startActivity(intent);
            }
        });


        updateTravel();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            // Handle the camera action
        } else if(id == R.id.solicitarViaje){
            Intent intent = new Intent(MainActivity.this, RequestTravel.class);
            startActivity(intent);
        } else if (id == R.id.nuevoEnvio) {
            Intent intent = new Intent(MainActivity.this, BuscarViajeActivity.class);
            startActivity(intent);
        } else if (id == R.id.sign_out) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {

        ArrayList<Viaje> cList = new ArrayList<>();

        try {
            JSONArray subList = responseBody.getJSONArray("data");
            for(int i = 0; i < subList.length(); i++){
                JSONObject cJson = subList.getJSONObject(i);
                String name = cJson.getString("email");
                String origin = cJson.getString("origin");
                String destiny = cJson.getString("destiny");
                String date = cJson.getString("date");
                int capMax = cJson.getInt("capMax");
                int currentCap = cJson.getInt("capCurrent");
                int capReservada = 0;
                int cantidadViajantes = cJson.getInt("__v");;
                //Buscamos mi viaje
                String emailBuscado = Singletonazo.getInstance().getEmail();
                boolean encontro = false;
                JSONArray registerList = cJson.getJSONArray("registers");

                for(int j = 0;j < registerList.length(); j++){

                    JSONObject registradoActual = registerList.getJSONObject(j);
                    JSONObject travelData = registradoActual.getJSONObject("userId");
                    String email = travelData.getString("email");

                    encontro = email.compareTo(emailBuscado) == 0;

                    if(encontro){
                        capReservada = registradoActual.getInt("capacity");
                    }

                }

                Viaje v = new Viaje(date, origin, destiny, currentCap, capMax);
                v.setEspacioPedido(capReservada);
                v.setFecha(date);
                v.setCantidadViajantes(cantidadViajantes);
                cList.add(v);
            }

            tripsAdapter.clear();
            tripsAdapter.addAll(cList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateTravel(){
        tripsAdapter.clear();
        String uri = Singletonazo.getInstance().getIp() + ":3000/users/myTravels?email=" + Singletonazo.getInstance().getEmail();
        httpGetConnection= new HttpGetConnection(this);
        httpGetConnection.setUri(uri);
        httpGetConnection.execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateTravel();
    }

    @Override
    public void handleHttpError(String error) {
        System.out.println(error);
    }
}
