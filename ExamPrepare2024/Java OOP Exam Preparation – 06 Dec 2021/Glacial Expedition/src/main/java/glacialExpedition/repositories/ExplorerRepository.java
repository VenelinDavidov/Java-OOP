package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements  Repository <Explorer> {

    // explorer is unique in the collection!!!!!!!
    Map<String,Explorer> explorerMap;

    public ExplorerRepository() {
        this.explorerMap =  new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorerMap.values());
    }

    @Override
    public void add(Explorer explorer) {
        explorerMap.putIfAbsent(explorer.getName(), explorer);
    }

    @Override
    public boolean remove(Explorer explorer) {
        return explorerMap.remove(explorer.getName()) != null;
    }

    @Override
    public Explorer byName(String name) {
        return explorerMap.get(name);
    }
}
