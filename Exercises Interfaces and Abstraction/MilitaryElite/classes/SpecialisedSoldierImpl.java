package Exercise_InterfacesAndAbstraction.MilitaryElite.classes;

import Exercise_InterfacesAndAbstraction.MilitaryElite.enums.Corps;
import Exercise_InterfacesAndAbstraction.MilitaryElite.enums.ExceptionMessage;
import Exercise_InterfacesAndAbstraction.MilitaryElite.interfaces.SpecialisedSoldier;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    protected Corps corps;

    protected SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
    }

    public void setCorps(String corps) throws Exception {
        try {
            this.corps = Corps.valueOf(corps);
        } catch (Exception e) {
            throw new Exception(ExceptionMessage.INVALID_CORPS);
        }
    }

    @Override
    public Corps getCorps() {
        return this.corps;
    }
}
