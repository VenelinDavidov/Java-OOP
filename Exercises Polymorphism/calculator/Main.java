package Exercises_Polymorphism.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1 * 2 * 3 ms * 4 * mr / 2 end
        CalculationEngine engine = new CalculationEngine();
        InputInterpreter interpreter = Extensions.buildInterpreter(engine);

        String[] tokens = scanner.nextLine().split("\\s+");
        for (String token : tokens) {
            if(token.equals("end")){
                break;
            }
            interpreter.interpret(token);
        }

        System.out.println(engine.getCurrentResult());
    }
}