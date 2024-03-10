package Exercises_Polymorphism.calculator;

public class InputInterpreter {
    // дефинира клас, който може да интерпретира низ или в число, или в операция и съответно да извика двигателя
    private CalculationEngine engine;

    public InputInterpreter(CalculationEngine engine){
        this.engine = engine;
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input)); //2
        }catch (Exception ex){
            engine.pushOperation(this.getOperation(input)); // *
        }
        return true;
    }
    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new MultiplicationOperation();
        } else if(operation.equals("/")){
            return new DivisionOperation();
        } else if(operation.equals("ms")){
            return new MsOperation(engine.getMemory());
        } else if(operation.equals("mr")){
            return new MrOperation(engine.getMemory());
        }

        return null;
    }
}