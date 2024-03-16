package Skeletons.barracksWars;
import Skeletons.barracksWars.interfaces.Repository;
import Skeletons.barracksWars.interfaces.Runnable;
import Skeletons.barracksWars.interfaces.UnitFactory;
import Skeletons.barracksWars.core.Engine;
import Skeletons.barracksWars.core.factories.UnitFactoryImpl;
import Skeletons.barracksWars.data.UnitRepository;


public class Main {
    public static void main(String[] args)  {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }

    //Engine -> parse and execute commands
    //Repository -> военна единица : брой
    //archer: брой стрелци
    //pikeman: брой копиеносци
    //swordsman: брой мечоносците
    //UnitFactory: създаване на военни единици
}


