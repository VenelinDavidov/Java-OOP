package Exercises_Polymorphism.calculator;

public class Extensions {
    public static InputInterpreter buildInterpreter (CalculationEngine engine){
        return new InputInterpreter(engine);
    }
}
