package Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces;


import java.util.ArrayList;

public interface Commando {
    void addMission(Missions mission);

    ArrayList<Missions> getMissions();
    }
