package Exercises_Inheritance.Animals;

public class Dog extends Animal{
    public Dog(String name, int age, String gander) {
        super(name, age, gander);
    }
    public String produceSound(){
        return "Woof!";
    }
}
