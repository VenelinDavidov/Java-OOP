package restaurant;

import restaurant.core.Controller;
import restaurant.core.ControllerImpl;
import restaurant.core.Engine;
import restaurant.core.EngineImpl;

public class Main {

    public static void main(String[] args) {

        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
