package com.argentruck.argentruck_cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ClientsActivity extends AppCompatActivity {

    private ListView mClientsList;
    private ClientsAdapter clientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Instancia del ListView.
        mClientsList = (ListView) findViewById(R.id.clients_list);

        // Inicializar el adaptador con la fuente de datos.
        clientsAdapter = new ClientsAdapter(this,
                ClientsRepository.getInstance().getClients());

        //Relacionando la lista con el adaptador
        mClientsList.setAdapter(clientsAdapter);

        // Eventos
        mClientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ClientsActivity.this, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}
