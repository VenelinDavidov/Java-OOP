package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.Collections;

public class OperationImpl implements Operation{

    // method
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {

        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig()) {
                if (spot.getExhibits().isEmpty()) break;
                String exhibit = spot.getExhibits().iterator().next();
                discoverer.dig();
                discoverer.getMuseum().getExhibits().add(exhibit);
                spot.getExhibits().remove(exhibit);

            }
        }
    }
}

//        Collection<String> exhibits = spot.getExhibits();
//        for (Discoverer discoverer : discoverers) {
//             while (discoverer.canDig() && exhibits.iterator().hasNext()){
//              discoverer.dig();
//              String currentExhibit = exhibits.iterator().next();
//              discoverer.getMuseum().getExhibits().add(currentExhibit);
//              exhibits.remove(currentExhibit);
//             }
//        }
//    }
//}
