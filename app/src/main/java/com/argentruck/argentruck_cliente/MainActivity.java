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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTripActivity.class);
                startActivity(intent);
            }
        });

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
        } else if (id == R.id.nuevoEnvio) {
            Intent intent = new Intent(MainActivity.this, BuscarViajeActivity.class);
            startActivity(intent);
        } else if (id == R.id.sign_out) {

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
                //TODO: Arreglar esta wea
                Viaje v = new Viaje(date, origin, destiny, 10, 20);
                v.setEspacioPedido(5);
                v.setFecha(date);
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
