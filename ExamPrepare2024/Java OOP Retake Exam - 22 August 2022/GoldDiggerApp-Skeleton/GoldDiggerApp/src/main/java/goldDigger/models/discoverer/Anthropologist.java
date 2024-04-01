package goldDigger.models.discoverer;

public class Anthropologist extends  BaseDiscoverer{
    private static final double ANTHROPOLOGIST_ENERGY = 40;
    public Anthropologist(String name) {
        super(name, ANTHROPOLOGIST_ENERGY);
    }
}
