package Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces;

import Exercise_InterfacesAndAbstraction.MilitaryElite.enums.States;

public interface Missions {
    String getCodeName();
     States getState();
     void completeMission();
}
