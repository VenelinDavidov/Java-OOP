package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    //Map
    private Map<String, Gun> guns;

   // constructor
    public GunRepository() {
        guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return guns.values();
    }

    @Override
    public void add(Gun model) {
        guns.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        return guns.remove(model.getName()) != null;
    }

    @Override
    public Gun find(String name) {
        return guns.get(name);
    }
}
