package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private int count;

    //constructor
    public ControllerImpl() {
        diverRepository = new DiverRepository();
        divingSiteRepository = new DivingSiteRepository();
    }

    //method
    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        switch (kind) {
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }

        diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        for (String seaCreature : seaCreatures) {
            divingSite.getSeaCreatures().add(seaCreature);
        }
//        Collection<String> dividingSiteCreatures =divingSite.getSeaCreatures();
//        Collections.addAll(dividingSiteCreatures, seaCreatures);
        divingSiteRepository.add(divingSite);

        return String.format(DIVING_SITE_ADDED, siteName);
    }
    //Remove
    @Override
    public String removeDiver(String diverName) {
        Diver diver = diverRepository.byName(diverName);
        if (diver == null) {
            String errorMessage = String.format(DIVER_DOES_NOT_EXIST, diverName);
            throw new IllegalArgumentException(errorMessage);
        }
        diverRepository.remove(diver);

        return String.format(DIVER_REMOVE, diverName);
    }

    //StartDiving
    @Override
    public String startDiving(String siteName) {
       final List<Diver> suitableDivers = this.diverRepository
                .getCollection()
                .stream()
                .filter(diver -> diver.getOxygen() > 30)
                .collect(Collectors.toList());

        if (suitableDivers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }

        DivingSite divingSite = divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        diving.searching(divingSite, suitableDivers);

        long removedDiving = suitableDivers.stream()  //уморени
                .filter(diver -> diver.getOxygen() == 0)
                .count();
        this.count++;

        return String.format(SITE_DIVING, siteName, removedDiving);
    }

    @Override
    public String getStatistics() {
       StringBuilder sb = new StringBuilder();

       sb.append(String.format(FINAL_DIVING_SITES,this.count))
               .append(System.lineSeparator());
       sb.append(String.format(FINAL_DIVERS_STATISTICS))
               .append(System.lineSeparator());

       //интерираме по водолазите
        Collection<Diver> divers = diverRepository.getCollection();
        for (Diver diver : divers) {
            sb.append(String.format(FINAL_DIVER_NAME, diver.getName()))
                    .append(System.lineSeparator());
            sb.append(String.format(FINAL_DIVER_OXYGEN,diver.getOxygen()))
                    .append(System.lineSeparator());
            if (diver.getSeaCatch().getSeaCreatures().isEmpty()){
                sb.append(String.format(FINAL_DIVER_CATCH, "None"))
                        .append(System.lineSeparator());
            }else {
                sb.append(String.format(FINAL_DIVER_CATCH,
                        String.join(FINAL_DIVER_CATCH_DELIMITER, diver.getSeaCatch().getSeaCreatures())));

            }
        }
        return sb.toString().trim();
    }
}
