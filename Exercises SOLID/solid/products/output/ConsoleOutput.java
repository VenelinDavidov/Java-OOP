package Exercises_SOLID.solid.products.output;

import Exercises_SOLID.solid.products.Product;
import Exercises_SOLID.solid.products.calculator.Calculator;
import Exercises_SOLID.solid.products.calculator.CalorieCalculator;

import java.util.List;

public class ConsoleOutput implements Output {
    private Calculator calculator;
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

    //constructor
    public ConsoleOutput(CalorieCalculator calorieCalculator) {
        this.calculator = calorieCalculator;
    }

     //print
    public void outputSum(List<Product> products) {
        System.out.printf((SUM) + "%n", calculator.total(products));
    }

    public void outputAverage (List<Product> products) {
        System.out.printf((AVERAGE) + "%n", calculator.average(products));
    }
}
