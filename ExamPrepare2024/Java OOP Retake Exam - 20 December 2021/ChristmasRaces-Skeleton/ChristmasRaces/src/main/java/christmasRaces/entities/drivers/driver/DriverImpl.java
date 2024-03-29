package christmasRaces.entities.drivers.driver;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.drivers.Driver;

public class DriverImpl implements Driver {

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;



    //Constructor


    public DriverImpl(String name) {
        this.setName(name);
        this.canParticipate = false;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            String message = String.format(ExceptionMessages.INVALID_NAME, name, 5);
            throw new IllegalArgumentException(message);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        }
        this.car = car;
    }

    @Override
    public void winRace() {
      this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return car != null;
    }
}
