package Exercise_InterfacesAndAbstraction.MilitaryElite.classes;

import Exercise_InterfacesAndAbstraction.MilitaryElite.classes.SoldierImpl;
import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Name: %s %s Id: %d", firstName, lastName, id));
        result.append(System.lineSeparator()).append(String.format("Code Number: %s", codeNumber));
        return result.toString();
    }
}