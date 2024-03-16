package Skeletons.barracksWars.core.commands;

import Skeletons.barracksWars.interfaces.Executable;
import Skeletons.barracksWars.interfaces.Repository;
import Skeletons.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    public Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected String[] getData() {
        return data;
    }

    protected Repository getRepository() {
        return repository;
    }

    protected UnitFactory getUnitFactory() {
        return unitFactory;
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        return null;
    }
}

