package restaurant.repositories;

import restaurant.models.waiter.Waiter;

import java.util.*;

public class WaiterRepository implements Repository<Waiter>{

    private Map<String,Waiter> waiters;

    //constructor


    public WaiterRepository() {
        this.waiters = new LinkedHashMap<>();
    }

    //methods
    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(waiters.values());
    }

    @Override
    public void add(Waiter waiter) {
      waiters.putIfAbsent(waiter.getName(), waiter);
    }

    @Override
    public boolean remove(Waiter waiter) {
        return waiters.remove(waiter.getName()) != null;
    }

    @Override
    public Waiter byName(String name) {
        return waiters.get(name);
    }
}
