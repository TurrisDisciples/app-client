package com.argentruck.argentruck_cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de Clients
 */
public class ClientsRepository {
    private static ClientsRepository repository = new ClientsRepository();
    private HashMap<String, Client> Clients = new HashMap<>();

    public static ClientsRepository getInstance() {
        return repository;
    }

    private ClientsRepository() {

    }

    public void saveClient(Client Client) {
        Clients.put(Client.getId(), Client);
    }

    public List<Client> getClients() {
        return new ArrayList<>(Clients.values());
    }
}

