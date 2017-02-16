package com.argentruck.argentruck_cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.argentruck.argentruck_cliente.entidades.Viaje;

import java.util.List;

public class TripsAdapter extends ArrayAdapter<Viaje> {
    public TripsAdapter(Context context) {
        super(context, 0);
    }

    private char contInicial = 'A';

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        // ¿Ya se infló este view?
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            convertView = inflater.inflate(
                    R.layout.trips_item_list,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.inicial = (ImageView) convertView.findViewById(R.id.image_view);
            holder.viaje = (TextView) convertView.findViewById(R.id.viaje);
            holder.capacidad = (TextView) convertView.findViewById(R.id.capacidad);
            holder.fecha = (TextView) convertView.findViewById(R.id.title);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Client actual.
        Viaje viaje = getItem(position);

        holder.inicial.setBackgroundResource(R.drawable.truck_waiting);
        holder.viaje.setText(viaje.getOrigen() + " a " + viaje.getDestino());
        if (viaje.getEspacioPedido() > 1)
            holder.capacidad.setText("Reservado " + viaje.getEspacioPedido() + " pallets");
        else
            holder.capacidad.setText("Reservado " + viaje.getEspacioPedido() + " pallet");
        holder.fecha.setText(viaje.getFecha());

        return convertView;
    }

    static class ViewHolder {
        ImageView inicial;
        TextView viaje;
        TextView capacidad;
        TextView fecha;
    }
}
