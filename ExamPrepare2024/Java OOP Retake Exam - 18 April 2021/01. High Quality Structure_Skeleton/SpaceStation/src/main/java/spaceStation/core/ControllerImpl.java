package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanetsCount;


    // Constructor
    public ControllerImpl() {
        astronautRepository = new AstronautRepository();
        planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);
        //Енергични изледователи
        List<Astronaut> suitableDiscoverers = new ArrayList<>();
        for (Astronaut astronaut : astronautRepository.getModels()) {
            if (astronaut.getOxygen() > 60) {
                suitableDiscoverers.add(astronaut);
            }
        }
        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet planet1 = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planet1, suitableDiscoverers);

        long deadAstronauts = suitableDiscoverers.stream()
                .filter(discoverer -> discoverer.getOxygen() == 0)
                .count();
        exploredPlanetsCount++;

        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount)).append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        Collection<Astronaut> astronauts = astronautRepository.getModels();
        for (Astronaut astronaut : astronauts) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen())).append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()) {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none")).append(System.lineSeparator());
            } else {
                sb.append(String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astronaut.getBag().getItems())).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
