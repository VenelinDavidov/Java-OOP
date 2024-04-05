package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        //method

        Collection<String> seaCreature = divingSite.getSeaCreatures();
        for (Diver diver : divers) {
            while (diver.canDive() && seaCreature.iterator().hasNext()) {
                diver.shoot();
                String currentSeaCreature = seaCreature.iterator().next();
                diver.getSeaCatch().getSeaCreatures().add(currentSeaCreature);
                seaCreature.remove(currentSeaCreature);
            }
        }
    }
}
