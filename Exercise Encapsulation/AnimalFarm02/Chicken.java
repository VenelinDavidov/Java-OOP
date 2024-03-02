package Exercise_Encapsulation.AnimalFarm02;

public class Chicken {
    //fields

    private String name;  // one char
    private int age;  // 0-15;

    // methods


    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    private void setName(String name) {
        if (name.length() >= 1) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

    }

    private void setAge(int age) {
        if (age >= 0 && age <= 15) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }

    }


    public double productPerDay() {
       return this.calculateProductPerDay();
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", this.name, this.age, this.productPerDay());
    }

    private double calculateProductPerDay() {
        // or 6 yers < 11;
        if (this.age >= 0 && this.age <= 5) {
            return 2;
        } else if (this.age >= 6 && this.age <= 11) {
            return 1;
        } else {
            return 0.75;
        }
    }

}
