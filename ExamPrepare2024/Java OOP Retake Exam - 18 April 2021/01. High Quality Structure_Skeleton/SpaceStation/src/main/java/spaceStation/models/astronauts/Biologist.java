package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final double OXYGEN_BIOLOGIST = 70;
    private static final double OXYGEN_DECREASE = 5;

    public Biologist(String name) {
        super(name, OXYGEN_BIOLOGIST);
    }

    @Override
    public void breath() {
        setOxygen(getOxygen() - OXYGEN_DECREASE);

//        double newOxygen = getOxygen() -OXYGEN_DECREASE;
//        setOxygen(Math.max(0, newOxygen));


    }
}
