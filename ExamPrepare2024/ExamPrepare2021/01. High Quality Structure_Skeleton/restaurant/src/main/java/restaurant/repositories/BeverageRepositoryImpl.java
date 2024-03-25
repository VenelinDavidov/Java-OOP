package restaurant.repositories;


import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.*;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Map<String,Beverages> beveragesMap;
    //може с лист да се направи
    // private List<Beverages> beverages;

    public BeverageRepositoryImpl() {
        this.beveragesMap = new LinkedHashMap<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return beveragesMap.get(drinkName + " - " + drinkBrand);
//        return beveragesList.stream()
//                .filter(b->b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand))
//                .findFirst()
//                .orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(beveragesMap.values());
    }

    @Override
    public void add(Beverages entity) {
          beveragesMap.put(entity.getName(),entity);
    }
}
