package Exercises_Inheritance.Restaurant;

import java.math.BigDecimal;

public class Food extends Product {
    //fields  name price grams
    private  double grams;

    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
