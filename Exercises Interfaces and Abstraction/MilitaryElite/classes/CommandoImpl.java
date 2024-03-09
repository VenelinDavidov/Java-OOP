package Exercise_InterfacesAndAbstraction.MilitaryElite.classes;

import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.Commando;
import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.Missions;

import java.util.ArrayList;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private final ArrayList<Missions> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corps) throws Exception {
        super(id, firstName, lastName, salary);
        super.setCorps(corps);
        missions = new ArrayList<>();
    }

    @Override
    public void addMission(Missions mission) {
        this.missions.add(mission);
    }

    @Override
    public ArrayList<Missions> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Name: %s %s Id: %d Salary: %.2f", firstName, lastName, id, salary));
        result.append(System.lineSeparator());
        result.append(String.format("Corps: %s", corps));
        result.append(System.lineSeparator());
        result.append("Missions: ");
        this.missions.forEach(mission -> result.append(System.lineSeparator()).append("  ").append(mission));
        return result.toString();
    }

}