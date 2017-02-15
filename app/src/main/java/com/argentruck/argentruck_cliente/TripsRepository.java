package com.argentruck.argentruck_cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de Clients
 */
public class TripsRepository {
    private static TripsRepository repository = new TripsRepository();
    private HashMap<String, Client> otrara = new HashMap<>();

    public static TripsRepository getInstance() {
        return repository;
    }

    private TripsRepository() {
        saveClient(new Client("Buenos Aires", "Rosario", "100 tn", R.drawable.ic_menu_camera));
        saveClient(new Client("Formosa", "La quiaca", "20 pallets", R.drawable.ic_menu_gallery));
//        saveClient(new Client("asdasdasd", "Directora de Marketing", "Electrical Parts ltd", R.drawable.ic_menu_camera));
//        saveClient(new Client("wqeqdsad", "Diseñadora de Producto", "Creativa App", R.drawable.ic_menu_camera));
//        saveClient(new Client("xcxzczxcas", "Supervisor de Ventas", "Neumáticos Press", R.drawable.ic_menu_camera));
//        saveClient(new Client("Jcvvxcxa", "CEO", "Banco Nacional", R.drawable.ic_menu_camera));
//        saveClient(new Client("asdqweqes", "CTO", "Cooperativa Verde", R.drawable.ic_menu_camera));
//        saveClient(new Client("Alexa Giraldo", "Client Programmer", "Frutisofy", R.drawable.ic_menu_camera));
//        saveClient(new Client("Linda Murillo", "Directora de Marketing", "Seguros Boliver", R.drawable.ic_menu_camera));
//        saveClient(new Client("Lizeth Astrada", "CEO", "Concesionario Motolox", R.drawable.ic_menu_camera));
    }

    private void saveClient(Client Client) {
        otrara.put(Client.getId(), Client);
    }

    public List<Client> getClients() {
        return new ArrayList<>(otrara.values());
    }
}

