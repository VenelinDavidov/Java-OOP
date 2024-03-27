package catHouse.entities.cat;

import catHouse.common.ExceptionMessages;

public abstract class BaseCat implements Cat {

    private String name;
    private String breed;
    private int kilograms;
    private double price;

    // constructor

    public BaseCat(String name, String breed, int kilograms, double price) {
        this.setName(name);
        this.setBreed(breed);     //порода
        this.setPrice(price);
        this.setKilograms(kilograms);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            String message = ExceptionMessages.CAT_NAME_NULL_OR_EMPTY;
            throw new NullPointerException(message);
        }
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            String message = ExceptionMessages.CAT_BREED_CANNOT_BE_NULL_OR_EMPTY;
            throw new NullPointerException(message);
        }
        this.breed = breed;
    }

    @Override
    public int getKilograms() {
        return this.kilograms;
    }

    public void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            String message = ExceptionMessages.CAT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO;
            throw new IllegalArgumentException(message);
        }
        this.price = price;
    }

    @Override
    abstract public void eating();    //


}
