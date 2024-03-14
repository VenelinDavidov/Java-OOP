package Exercises_SOLID.solid.products.calculator;

import Exercises_SOLID.solid.products.Product;

import java.util.List;

public interface Calculator {
    double total(List<Product> products);
    double average (List<Product> products);
}
