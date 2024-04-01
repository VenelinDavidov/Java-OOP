package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.*;

public class DiscovererRepository implements Repository<Discoverer> {
    private Map<String, Discoverer> discoverers;

    // constructor
    public DiscovererRepository() {
        this.discoverers = new LinkedHashMap<>();
    }

    //methods
    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers.values());
    }

    @Override
    public void add(Discoverer discoverer) {
        discoverers.putIfAbsent(discoverer.getName(),discoverer);
    }

    @Override
    public boolean remove(Discoverer discoverer) {
        return discoverers.remove(discoverer.getName()) != null;
    }

    @Override
    public Discoverer byName(String name) {
        return discoverers.get(name);
    }
}
