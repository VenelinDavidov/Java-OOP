package glacialExpedition.models.states;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static glacialExpedition.common.ExceptionMessages.STATE_NAME_NULL_OR_EMPTY;

public class StateImpl implements  State{

    // fields
     private String name;
     private Collection<String> exhibits;

     //constructor
    public StateImpl(String name) {

     this.setName(name);
     this.exhibits = new ArrayList<>();
    }

    //setter name
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(STATE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    // getters
    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }
}
