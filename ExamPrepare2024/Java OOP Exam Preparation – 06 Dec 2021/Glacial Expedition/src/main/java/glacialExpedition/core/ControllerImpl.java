package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    //fields
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStatesCount;

    //constructor
    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            String message = String.format(EXPLORER_DOES_NOT_EXIST, explorerName);
            throw new IllegalArgumentException(message);
        }
        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> suitableExplorers = explorerRepository
                .getCollection()
                .stream()
                .filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, suitableExplorers);

        long retiredExplorers = suitableExplorers.stream()
                .filter(explorer -> explorer.getEnergy() == 0)
                .count();
        exploredStatesCount++;

        return String.format(STATE_EXPLORER, stateName, retiredExplorers);

    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_STATE_EXPLORED, exploredStatesCount)).append(System.lineSeparator());
        sb.append(String.format(FINAL_EXPLORER_INFO)).append(System.lineSeparator());

        Collection<Explorer> explorers = explorerRepository.getCollection();
        for (Explorer explorer : explorers) {
            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());

            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"))
                        .append(System.lineSeparator());
            } else {
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS,
                         String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorer.getSuitcase().getExhibits())))
                        .append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
