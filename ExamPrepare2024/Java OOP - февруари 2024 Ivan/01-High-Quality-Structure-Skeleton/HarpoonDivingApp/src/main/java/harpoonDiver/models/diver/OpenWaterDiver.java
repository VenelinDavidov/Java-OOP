package harpoonDiver.models.diver;

public class OpenWaterDiver extends  BaseDiver{

    private static  final double OPEN_WATER_DIVER = 30;

    public OpenWaterDiver(String name) {
        super(name, OPEN_WATER_DIVER);
    }
}
