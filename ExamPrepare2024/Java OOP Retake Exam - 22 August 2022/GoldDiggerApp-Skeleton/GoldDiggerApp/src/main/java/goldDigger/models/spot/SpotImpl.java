package goldDigger.models.spot;

import java.util.ArrayList;
import java.util.Collection;

import static goldDigger.common.ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY;

public class SpotImpl implements Spot{
    private String name;
    private Collection<String> exhibits;  //експонати

    //constructor
    public SpotImpl(String name) {
      setName(name);
      exhibits = new ArrayList<>();
    }

    //setter
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(SPOT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    // getter
    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }
}
