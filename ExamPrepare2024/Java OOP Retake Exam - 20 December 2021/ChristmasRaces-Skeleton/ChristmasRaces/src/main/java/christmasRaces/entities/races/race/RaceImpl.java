package christmasRaces.entities.races.race;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.races.Race;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {
    private String  name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    //setter
    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5){
            String message = String.format(ExceptionMessages.INVALID_NAME,name,5);
            throw new IllegalArgumentException(message);
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1 ){
            String message = String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1);
            throw new IllegalArgumentException(message);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
          if (driver == null ){
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
          } else if (!driver.getCanParticipate()){
              String message = String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName());
              throw new IllegalArgumentException(message);
          } else if (drivers.contains(driver)) {
              String message = String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,driver.getName(),this.name);
              throw new IllegalArgumentException(message);
          }
          drivers.add(driver);
    }
}
