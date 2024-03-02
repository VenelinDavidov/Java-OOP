package Exercises_Inheritance.Animals;

public class Cat extends Animal{
    public Cat(String name, int age, String gander) {
        super(name, age, gander);
    }
    public String produceSound(){
        return "Meow meow";
    }
}
