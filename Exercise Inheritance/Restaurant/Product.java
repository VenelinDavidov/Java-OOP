package Exercises_Inheritance.Restaurant;

import java.math.BigDecimal;

public class Product {
    // filds
    private String name;
    private BigDecimal price;

    // constructor

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    // getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
