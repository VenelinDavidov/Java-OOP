package Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces;

import java.util.ArrayList;

public interface Engineer {
    void addRepair(Repair repair);
    ArrayList<Repair> getRepairs();
}
