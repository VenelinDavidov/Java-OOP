package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

//Гмуркане
public interface Diving {

    void searching(DivingSite divingSite, Collection<Diver> divers);
}
