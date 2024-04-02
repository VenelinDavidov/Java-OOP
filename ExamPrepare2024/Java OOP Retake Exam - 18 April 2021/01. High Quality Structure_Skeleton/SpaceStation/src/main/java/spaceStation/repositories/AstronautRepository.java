package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class AstronautRepository implements Repository<Astronaut>{

    private Map<String, Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new LinkedHashMap<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts.values());
    }

    @Override
    public void add(Astronaut astronaut) {
      astronauts.putIfAbsent(astronaut.getName(), astronaut);
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        return astronauts.remove(astronaut.getName()) != null;
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.get(name);
    }
}
