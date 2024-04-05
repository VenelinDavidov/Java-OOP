package harpoonDiver.models.divingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static harpoonDiver.common.ExceptionMessages.SITE_NAME_NULL_OR_EMPTY;

public class DivingSiteImpl implements DivingSite {
    private String name;
    private Collection<String> seaCreatures;

    //constructor


    public DivingSiteImpl(String name) {
        setName(name);
        seaCreatures = new ArrayList<>();
    }

    //setter


    public void setName(String name) {
        if ( name == null || name.trim().isEmpty()){
            throw new NullPointerException(SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    //methods
    @Override
    public Collection<String> getSeaCreatures() {
            return seaCreatures;
    }

    @Override
    public String getName() {
        return name;
    }
}
