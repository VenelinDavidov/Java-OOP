package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Suitcase;
import glacialExpedition.models.suitcases.Carton;

import static glacialExpedition.common.ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY;

public abstract class BaseExplorer implements  Explorer{





    private static final int ENERGY_COST = 15;
    // Firlds
     private String name;
     private double energy;
     private Suitcase suitcase;

    //Constructor
    protected BaseExplorer(String name, double energy) {
      this.setName(name);
      this.setEnergy(energy);
      this.suitcase = new Carton();
    }

    //setter
    protected void setEnergy(double energy) {
        if (energy < 0){
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setSuitcase(Suitcase suitcase) {
        this.suitcase = suitcase;
    }


    //getter
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean canSearch() {
        return energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {

       energy = Math.max(0, energy - ENERGY_COST);

//       energy = energy - ENERGY_COST;
//       if (energy < 0){
//           energy = 0;
//       }


    }
}
