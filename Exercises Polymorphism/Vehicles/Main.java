package Exercises_Polymorphism.Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>(); //Map

        vehicleMap.put("Car", getVehicle(scanner));
        vehicleMap.put("Truck", getVehicle(scanner));
        vehicleMap.put("Bus", getVehicle(scanner));

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfCommands; i++) {
            //Car 15 0.3
            //Truck 100 0.9

            String[] command = scanner.nextLine().split("\\s+");
            String commandName = command[0];
            String vehicleType = command[1];
            double argument = Double.parseDouble(command[2]);

            switch (commandName) {
                case "DriveEmpty":
                    System.out.println(vehicleMap.get(vehicleType).drive(argument));
                    break;
                case "Drive":
                    System.out.println(vehicleMap.get(vehicleType).driveWithAC(argument));
                    break;
                case "Refuel":
                    vehicleMap.get(vehicleType).refuel(argument);
                    break;
            }
        }

        vehicleMap.values().forEach(System.out::println);
    }

    //methods
    private static Vehicle getVehicle(Scanner scanner) {
        String[] vehicleData = scanner.nextLine().split("\\s+");
        String vehicleType = vehicleData[0];
        double fuelAmount = Double.parseDouble(vehicleData[1]);
        double fuelConsumption = Double.parseDouble(vehicleData[2]);
        double tankCapacity = Double.parseDouble(vehicleData[3]);

        switch (vehicleType) {
            case "Car":
                return new Car(fuelAmount, fuelConsumption, tankCapacity);
            case "Truck":
                return new Truck(fuelAmount, fuelConsumption, tankCapacity);
            case "Bus":
                return new Bus(fuelAmount,fuelConsumption,tankCapacity);

            default:
                throw new IllegalArgumentException("Missing car");
        }
    }
}