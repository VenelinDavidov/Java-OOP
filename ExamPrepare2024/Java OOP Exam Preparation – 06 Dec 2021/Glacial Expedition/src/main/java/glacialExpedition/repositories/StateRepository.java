package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.*;

public class StateRepository implements Repository<State> {

    //state is unique in the collection.
    Map<String, State> stateMap;


    public StateRepository() {
        this.stateMap = new LinkedHashMap<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(stateMap.values());
    }

    @Override
    public void add(State state) {
      stateMap.putIfAbsent(state.getName(),state);
    }

    @Override
    public boolean remove(State state) {
        return stateMap.remove(state.getName()) != null;
    }

    @Override
    public State byName(String name) {
        return stateMap.get(name);
    }
}
