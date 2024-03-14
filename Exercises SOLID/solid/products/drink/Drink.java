package Exercises_SOLID.solid.products.drink;

import Exercises_SOLID.solid.products.Product;

public abstract class Drink implements Product {
    private double milliliters;
    private double caloriesPer100Gram;
    private  double density;

    // constructor


    public Drink(double milliliters, double caloriesPer100Gram, double density) {
        this.milliliters = milliliters;
        this.caloriesPer100Gram = caloriesPer100Gram;
        this.density = density;
    }

    // methods
    public Drink(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
    @Override
    public double getCalories() {
        double grams = milliliters * density;
        double sum = (caloriesPer100Gram / 100) * grams;
        return sum;
    }
    public double getLitters(){
        return milliliters * 1000;
    }

    @Override
    public double getKilograms() {
        return getLitters() * density;
    }

    // getter and setter

    public void setMilliliters(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getCaloriesPer100Gram() {
        return caloriesPer100Gram;
    }

    public void setCaloriesPer100Gram(double caloriesPer100Gram) {
        this.caloriesPer100Gram = caloriesPer100Gram;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}
