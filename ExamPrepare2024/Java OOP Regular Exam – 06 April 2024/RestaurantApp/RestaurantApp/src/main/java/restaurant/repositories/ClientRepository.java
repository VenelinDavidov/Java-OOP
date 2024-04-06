package restaurant.repositories;

import restaurant.models.client.Client;

import java.util.*;

public class ClientRepository implements Repository<Client> {

    private Map<String, Client> clients;

    //Constructor


    public ClientRepository() {
        this.clients = new LinkedHashMap<>();
    }

    //methods
    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(clients.values());
    }

    @Override
    public void add(Client client) {
        clients.putIfAbsent(client.getName(), client);
    }

    @Override
    public boolean remove(Client client) {
        return clients.remove(client.getName()) != null;
    }

    @Override
    public Client byName(String name) {
        return clients.get(name);
    }
}
