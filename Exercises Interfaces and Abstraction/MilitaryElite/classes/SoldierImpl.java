package Exercise_InterfacesAndAbstraction.MilitaryElite.classes;

import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
    // fields
    protected  int id;
    protected String firstName;
    protected String  lastName;

    // Constructor
    protected SoldierImpl(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // methods
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
}
