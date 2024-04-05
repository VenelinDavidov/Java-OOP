package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.*;

public class DivingSiteRepository implements Repository<DivingSite> {
    private final Map<String, DivingSite> sites;

    public DivingSiteRepository() {
        sites = new LinkedHashMap<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(sites.values());
    }

    @Override
    public void add(DivingSite divingSite) {
        sites.putIfAbsent(divingSite.getName(), divingSite);
    }

    @Override
    public boolean remove(DivingSite divingSite) {
        return sites.remove(divingSite.getName()) != null;
    }

    @Override
    public DivingSite byName(String name) {
        return sites.get(name);
    }
}
