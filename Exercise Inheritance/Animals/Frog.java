package Exercises_Inheritance.Animals;

public class Frog extends  Animal{
    public Frog(String name, int age, String gander) {
        super(name, age, gander);
    }
    public String produceSound(){
        return "Ribbit";
    }
}
