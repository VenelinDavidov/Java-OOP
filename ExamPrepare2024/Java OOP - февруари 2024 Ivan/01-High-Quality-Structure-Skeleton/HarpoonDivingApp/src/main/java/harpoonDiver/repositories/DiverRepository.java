package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.*;

public class DiverRepository implements Repository<Diver>{

  private Map<String, Diver> divers;

    public DiverRepository() {
        divers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(divers.values());
    }

    @Override
    public void add(Diver diver) {
        divers.putIfAbsent(diver.getName(), diver);
    }

    @Override
    public boolean remove(Diver diver) {
        return divers.remove(diver.getName()) != null;
     }

    @Override
    public Diver byName(String name) {
        return divers.get(name);
    }
}
