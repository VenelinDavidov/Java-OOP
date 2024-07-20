package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {


    public static int NATURAL_EXPLORER_ENERGY = 60;
    public static final int DECREASE_ENERGY_UNITS = 7;

    public NaturalExplorer(String name) {
        super(name, NATURAL_EXPLORER_ENERGY);
    }

    @Override
    public void search() {

        double energy = getEnergy();

        if (energy > 0) {
            setEnergy(energy - DECREASE_ENERGY_UNITS);
        } else {
            NATURAL_EXPLORER_ENERGY = 0;
        }
    }
}
