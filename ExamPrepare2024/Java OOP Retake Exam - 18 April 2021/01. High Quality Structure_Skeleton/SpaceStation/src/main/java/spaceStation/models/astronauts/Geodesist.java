package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final double OXYGEN_GEODESIST = 50;
    public Geodesist(String name) {
        super(name, OXYGEN_GEODESIST);
    }
}
