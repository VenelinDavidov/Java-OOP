package christmasRaces.entities.cars.cars;


import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public abstract class BaseCar implements Car {
    private String  model;
    private int horsePower;
    private double cubicCentimeters;

    //constructor

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }
    //setter


    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4 ){
            String message = String.format(ExceptionMessages.INVALID_MODEL,model, 4);
            throw new IllegalArgumentException(message);
        }
        this.model = model;
    }
    protected abstract void  isValidHorsepower(int horsePower); // дава достъп до всички наследници;
    private void setHorsePower(int horsePower) {

        this.isValidHorsepower(horsePower);
        this.horsePower = horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    //getter

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters /  horsePower * laps;
    }
}
