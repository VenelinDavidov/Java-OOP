package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseHouse implements House {
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }
    // Method to calculate the sum of softness of all toys
    @Override
    public int sumSoftness() {
        int sum =0;
        for (Toy toy : toys) {
            sum += toy.getSoftness();
        }
        return sum;
    }

    // // Method to add a cat to the house
    @Override
    public void addCat(Cat cat) {
        if (this.getCats().size() >= capacity){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        this.getCats().add(cat);
    }
    // Method to remove a cat from the house
    @Override
    public void removeCat(Cat cat) {
     this.getCats().remove(cat);
    }
    // Method to buy (add) a toy to the house
    @Override
    public void buyToy(Toy toy) {
       getToys().add(toy);
    }
    // Method to feed all cats in the house
    @Override
    public void feeding() {
        for (Cat cat : this.getCats()) {
            cat.eating();
        }
    }
    // Method to get statistics about the house
    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s:%n",this.getName(),this.getClass().getSimpleName()));
        builder.append("Cats: ");
        if (this.getCats().isEmpty()){
            builder.append("none");
        }else {
            builder.append(this.getCats().stream()
                    .map(Cat::getName)
                    .collect(Collectors.joining(" ")).trim());
            builder.append(System.lineSeparator());
        }
        builder.append(String.format("Toys: %d Softness: %d",this.getToys().size(),this.sumSoftness()));
        return builder.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }

    public int getCapacity() {
        return this.cats.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
