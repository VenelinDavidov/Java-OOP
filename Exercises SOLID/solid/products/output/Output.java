package Exercises_SOLID.solid.products.output;

import Exercises_SOLID.solid.products.Product;

import java.util.List;

public interface Output {
    void outputSum(List<Product> productList);
    void outputAverage(List<Product> productList);

}
