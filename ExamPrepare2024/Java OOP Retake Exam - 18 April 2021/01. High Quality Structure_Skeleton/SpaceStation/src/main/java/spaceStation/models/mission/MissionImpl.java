package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.List;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {
//        for (Astronaut astronaut : astronauts) {
//            if (astronaut.getOxygen() > 0) {
//                for (String item : planet.getItems()) {
//                    astronaut.breath();
//                    if (!astronaut.canBreath()) {
//                        astronauts.remove(astronaut);
//                        break;
//                    }
//                    astronaut.getBag().getItems().add(item);
//                    planet.getItems().remove(item);
//
//                }
//            }
//        }
//    }
//}
        for (int index = 1; index < astronauts.size(); index++) {
            Astronaut currentAstronauts = astronauts.get(index);
            for (int item = 0; item < planet.getItems().size(); item++) {
                String currentItem = planet.getItems().get(item);
                currentAstronauts.getBag().getItems().add(currentItem);
                planet.getItems().remove(currentItem);
                item--;
                currentAstronauts.breath();
                if (!currentAstronauts.canBreath()) {
                    astronauts.remove(currentAstronauts);
                    index--;
                    break;
                }
            }
        }
    }
}
