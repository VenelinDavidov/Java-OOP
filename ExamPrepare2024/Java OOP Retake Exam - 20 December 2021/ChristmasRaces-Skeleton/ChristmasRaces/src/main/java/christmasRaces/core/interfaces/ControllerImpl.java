package christmasRaces.core.interfaces;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.cars.MuscleCar;
import christmasRaces.entities.cars.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.driver.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.race.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ControllerImpl implements Controller {


    //A ControllerImpl should take the following values upon initialization in the specified order.
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    //Constructor

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }
    //Methods

    //Create Driver
    @Override
    public String createDriver(String driverName) {
        // If a driver with the given name already exists in the driver repository
        // if (driverRepository.getByName(driverName) != null)
        for (Driver driver : driverRepository.getAll()) {
            if (driver.getName().equals(driverName)) {
                String message = String.format(ExceptionMessages.DRIVER_EXISTS, driverName);
                throw new IllegalArgumentException(message);
            }
        }

        //driverRepository.add(new DriverImpl(driverName));
        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    //Create Car
    @Override
    public String createCar(String type, String model, int horsePower) {
         String carType = type.equals("Muscle") ? "MuscleCar" : "SportsCar";
        Car car = null;
        if (carType.equals("MuscleCar")){
            car = new MuscleCar(model,horsePower);
        } else {
            car = new SportsCar(model,horsePower);
        }
        //If the Car already exists in the appropriate repository;
        if (carRepository.getByName(model) != null) {
            String message = String.format(ExceptionMessages.CAR_EXISTS, model);
            throw new IllegalArgumentException(message);
        }
        //add car
        carRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED, carType,model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        // проверка дали шофьора съществува
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            String exceptionMessage = String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName);
            throw new IllegalArgumentException(exceptionMessage);
        }
        // проверка дали колата съществува
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            String exceptionMessage = String.format(ExceptionMessages.CAR_NOT_FOUND, carModel);
            throw new IllegalArgumentException(exceptionMessage);
        }
        // добавяме колата
        driver.addCar(car);
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        //  Race
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            String exceptionMessage = String.format(ExceptionMessages.RACE_NOT_FOUND, raceName);
            throw new IllegalArgumentException(exceptionMessage);
        }
        // Drive
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            String exceptionMessage = String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName);
            throw new IllegalArgumentException(exceptionMessage);
        }
        // Add
        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        //If the Race does not exist in RaceRepository
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            String exceptionMessage = String.format(ExceptionMessages.RACE_NOT_FOUND, raceName);
            throw new IllegalArgumentException(exceptionMessage);
        }
        //If the participants in the race are less than 3
        if (race.getDrivers().size() < 3) {
            String exceptionMessage = String.format(ExceptionMessages.RACE_INVALID, raceName, 3);
            throw new IllegalArgumentException(exceptionMessage);
        }
        //If everything is successful;
        Collection<Driver> drivers = race.getDrivers();
        int numberOfLaps = race.getLaps();

        List<Driver> winners = drivers.stream()
                .sorted((d1, d2) ->
                 Double.compare(d2.getCar().calculateRacePoints(numberOfLaps),
                 d1.getCar().calculateRacePoints(numberOfLaps)))
                .limit(3).collect(Collectors.toList());

        raceRepository.remove(race);

        Driver firstDrive = winners.get(0);
        Driver secondDrive = winners.get(1);
        Driver thirdDrive = winners.get(2);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDrive.getName(), race.getName()))
                .append(System.lineSeparator());
        builder.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDrive.getName(), race.getName()))
                .append(System.lineSeparator());
        builder.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDrive.getName(), race.getName()))
                .append(System.lineSeparator());
        return builder.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        //If the Race with the given name already exist;
        Race race = raceRepository.getByName(name);
        if (race != null) {
            String exceptionMessage = String.format(ExceptionMessages.RACE_EXISTS, name);
            throw new IllegalArgumentException(exceptionMessage);
        }
        //Creates a Race with the given name and laps and adds it to the RaceRepository.
        Race raceNew = new RaceImpl(name, laps);
        raceRepository.add(raceNew);
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
