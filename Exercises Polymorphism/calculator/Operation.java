package Exercises_Polymorphism.calculator;

public interface Operation {
    void addOperand(int operand);
    int getResult();
    boolean isCompleted();
}
