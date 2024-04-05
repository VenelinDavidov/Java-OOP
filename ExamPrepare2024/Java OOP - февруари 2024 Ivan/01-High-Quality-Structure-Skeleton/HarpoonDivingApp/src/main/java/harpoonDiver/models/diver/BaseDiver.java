package harpoonDiver.models.diver;

import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY;
import static harpoonDiver.common.ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseDiver implements Diver {
    private static final double OXYGEN_COST = 30;

    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    //constructor


    protected BaseDiver(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();

    }

    //setter
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    protected void setSeaCatch(SeaCatch seaCatch) {
        this.seaCatch = seaCatch;
    }

    // methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canDive() {
        return oxygen > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return seaCatch;
    }

    @Override
    public void shoot() {

        oxygen = Math.max(0, oxygen - OXYGEN_COST);

//        if (oxygen < 30){
//            oxygen = 0;
//        }else{
//            oxygen = oxygen - OXYGEN_COST;
//        }

//        setOxygen(getOxygen() - OXYGEN_COST);
//        if (oxygen < 0) {
//            oxygen = 0;
//        }

    }
}
