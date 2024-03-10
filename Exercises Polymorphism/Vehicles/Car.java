package Exercises_Polymorphism.Vehicles;

public class Car extends  Vehicle{
    private final static double ADDITIONAL_AC_CONSUMPTION = 0.9;
    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity,fuelConsumption,tankCapacity,ADDITIONAL_AC_CONSUMPTION);


        //3 super.setFuelConsumption(super.getFuelConsumption + ADDITIONAL_AC_CONSUMPTION);


    }
}
