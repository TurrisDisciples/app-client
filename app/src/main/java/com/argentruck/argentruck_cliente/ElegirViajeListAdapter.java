package com.argentruck.argentruck_cliente;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.argentruck.argentruck_cliente.entidades.Viaje;

/**
 * Created by demian on 12/02/17.
 */
public class ElegirViajeListAdapter extends ArrayAdapter<Viaje> {
        public ElegirViajeListAdapter(Context context) {
            super(context, R.layout.fragment_travel_select_item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View customView = layoutInflater.inflate(R.layout.fragment_travel_select_item, parent, false);

            Viaje viajeActual = getItem(position);

            TextView travelDirection = (TextView) customView.findViewById(R.id.ftsi_tv_travel);
            travelDirection.setText("Desde " + viajeActual.getOrigen() + " a " + viajeActual.getDestino());

            TextView travelOwner = (TextView) customView.findViewById(R.id.ftsi_tv_owner);
            travelOwner.setText("Conductor: " + viajeActual.getConductor());

            TextView travelDate = (TextView) customView.findViewById(R.id.ftsi_tv_date);
            travelDate.setText("Fecha: " + viajeActual.getFecha());

            TextView travelCarga = (TextView) customView.findViewById(R.id.ftsi_tv_carga);
            travelCarga.setText("Carga: " + viajeActual.getEspacioLibre());

            return customView;
        }
}

