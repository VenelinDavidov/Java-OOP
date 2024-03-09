package Exercise_InterfacesAndAbstraction.MilitaryElite.classes;

import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.Repair;

public class RepairImpl  implements Repair {

    private final String partName;
    private final int hoursWorked;

    public RepairImpl(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getPartName() {
        return null;
    }

    @Override
    public int getHoursWorked() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Part Name: %s Hours Worked: %d", partName, hoursWorked));
        return  result.toString();
    }
}
