package Exercises_Polymorphism.Vehicles;

import java.text.DecimalFormat;

public class Vehicle {

    protected double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;
    private double additionalACConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption,double tankCapacity,double additionalACConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
        this.additionalACConsumption = additionalACConsumption;
    }
    public String driveWithAC(double distance){
        setFuelConsumption(getFuelConsumption()+ additionalACConsumption);
        String  result = this.drive(distance);
        setFuelConsumption(getFuelConsumption() - additionalACConsumption);
        return result;
    }

    public String drive(double distance) {

        double fuelNeeded = distance * fuelConsumption;
        // проверяваме дали може да изминенм разстоянието;
        if(fuelNeeded > getFuelQuantity()){
            return  this.getClass().getSimpleName() + " needs refueling";
        }
        // изминаваме го - да намалин горивото

        setFuelQuantity(getFuelQuantity() - fuelNeeded);
        DecimalFormat df = new DecimalFormat("##.##");
        return String.format("%s travelled %s km",this.getClass().getSimpleName(), df.format(distance));
    }

    public void refuel(double litres) {
        if (litres <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }
        if (litres > this.tankCapacity){
            System.out.println("Cannot fit fuel in tank" );
            return;
        }
        this.fuelQuantity += litres;
    }
    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), fuelQuantity);
    }
}
