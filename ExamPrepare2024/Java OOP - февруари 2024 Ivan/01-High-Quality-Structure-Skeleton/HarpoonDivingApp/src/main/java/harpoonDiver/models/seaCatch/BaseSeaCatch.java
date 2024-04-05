package harpoonDiver.models.seaCatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class BaseSeaCatch implements SeaCatch {
    Collection<String> seaCreatures;

    public BaseSeaCatch() {
        seaCreatures = new ArrayList<>();
    }

    @Override
    public Collection<String> getSeaCreatures() {
        return seaCreatures;
    }

}
