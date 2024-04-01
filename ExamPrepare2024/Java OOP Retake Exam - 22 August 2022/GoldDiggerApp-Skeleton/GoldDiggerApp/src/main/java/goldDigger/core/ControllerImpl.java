package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int spotCount;

    //constructor

    public ControllerImpl() {
        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            String message = String.format(DISCOVERER_DOES_NOT_EXIST, discovererName);
            throw new IllegalArgumentException(message);
        }
        discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        Spot spot = spotRepository.byName(spotName);

        //Енергични изледователи
        List<Discoverer> suitableDiscoverers = new ArrayList<>();
        for (Discoverer discoverer : discovererRepository.getCollection()) {
            if (discoverer.getEnergy() > 45) {
                suitableDiscoverers.add(discoverer);
            }
        }
//        List<Discoverer> suitableDiscoverers = discovererRepository
//                .getCollection()
//                .stream()
//                .filter(discoverer -> discoverer.getEnergy() > 45)
//                .collect(Collectors.toList());

        //•	If you don't have any suitable discoverers
        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        //•	After a mission, you must return the following message
        Spot spot1 = spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot1,suitableDiscoverers);

//        int excludedDiscovererCount = 0;
//        for (Discoverer discoverer : suitableDiscoverers) {
//            discoverer.dig();
//            if (discoverer.getEnergy() == 0) {
//                discovererRepository.remove(discoverer);
//                excludedDiscovererCount++;
//            }
//        }
        long tiredDiscoverers = suitableDiscoverers.stream()
                .filter(discoverer -> discoverer.getEnergy() == 0)
                .count();
         spotCount++;
        return String.format(INSPECT_SPOT,spotName, tiredDiscoverers );

    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(FINAL_SPOT_INSPECT, spotCount)).append(System.lineSeparator());
        builder.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for (Discoverer discoverer : discoverers) {
            builder.append(String.format(FINAL_DISCOVERER_NAME,discoverer.getName())).append(System.lineSeparator());
            builder.append(String.format( FINAL_DISCOVERER_ENERGY,discoverer.getEnergy())).append(System.lineSeparator());
            if (discoverer.getMuseum().getExhibits().isEmpty()){
                builder.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None")).append(System.lineSeparator());
            } else {
                builder.append(String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER , discoverer.getMuseum().getExhibits()))
                        .append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }
}
