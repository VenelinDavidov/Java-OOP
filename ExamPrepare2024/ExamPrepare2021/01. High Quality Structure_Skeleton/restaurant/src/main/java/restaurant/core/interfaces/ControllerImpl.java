package restaurant.core.interfaces;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

import javax.xml.namespace.QName;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private final HealthFoodRepository<HealthyFood> healthFoodRepository;
    private final BeverageRepository<Beverages> beverageRepository;
    private final TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {

        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        //Creates a food with the correct type
        HealthyFood food = type.equals("Salad")
                ? new Salad(name, price)
                : new VeganBiscuits(name, price);
        // food is created successfully add it to the food repository and returns:
        HealthyFood previousAdded = this.healthFoodRepository.foodByName(name);

        if (previousAdded == null) {
            this.healthFoodRepository.add(food);
            return String.format(OutputMessages.FOOD_ADDED, name);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = type.equals("Fresh")
                ? new Fresh(name, counter, brand)
                : new Smoothie(name, counter, brand);

        Beverages previousAdded = this.beverageRepository.beverageByName(name, brand);

        if (previousAdded == null) {
            this.beverageRepository.add(beverage);
            return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("Indoors")
                ? new Indoors(tableNumber, capacity)
                : new InGarden(tableNumber, capacity);

        Table previousAdded = this.tableRepository.byNumber(tableNumber);

        if (previousAdded == null) {
            this.tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED, tableNumber);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection<Table> tables = this.tableRepository.getAllEntities();
        Table table = tables.stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        String message = String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);

        if (table != null) {
            table.reserve(numberOfPeople);
            message = String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
        }
        return message;
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        //You first check if the table exists.
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        // check food
        HealthyFood food = this.healthFoodRepository.foodByName(healthyFoodName);
        if (food == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName, tableNumber);
        }
        //In other case orders the food for that table and returns:
        table.orderHealthy(food);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,healthyFoodName,tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }
       Beverages beverage = this.beverageRepository.beverageByName(name,brand);
        if (beverage == null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand);
        }
        table.orderBeverages(beverage);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
       Table table = this.tableRepository.byNumber(tableNumber);
       double bill = table.bill();
       totalIncome +=bill;
       return String.format(OutputMessages.BILL,tableNumber,bill);
    }

    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,totalIncome);
    }
}
