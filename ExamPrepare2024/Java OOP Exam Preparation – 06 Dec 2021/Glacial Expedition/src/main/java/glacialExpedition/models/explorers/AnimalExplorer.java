package glacialExpedition.models.explorers;

public class AnimalExplorer extends  BaseExplorer{



    public static int ANIMAL_EXPLORER_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, ANIMAL_EXPLORER_ENERGY);
    }
}
