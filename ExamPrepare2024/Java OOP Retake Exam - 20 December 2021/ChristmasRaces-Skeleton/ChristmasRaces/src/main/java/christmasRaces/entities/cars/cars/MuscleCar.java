package christmasRaces.entities.cars.cars;

import christmasRaces.common.ExceptionMessages;



public class MuscleCar extends BaseCar {
    private static final double DEFAULT_CUBIC_CENTIMETERS = 5000;
    private static final int MIN_HORSEPOWER = 400;
    private static final int MAX_HORSEPOWER = 600;


    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    @Override
    protected void isValidHorsepower(int horsePower) {
        if (horsePower < MIN_HORSEPOWER || horsePower > MAX_HORSEPOWER){
            String message = String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower);
            throw new IllegalArgumentException(message);
        }
    }
}
