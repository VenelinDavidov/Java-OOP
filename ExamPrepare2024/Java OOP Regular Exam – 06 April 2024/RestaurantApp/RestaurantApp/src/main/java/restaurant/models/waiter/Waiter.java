package restaurant.models.waiter;

import restaurant.models.orders.TakenOrders;

//сервитьор

public interface Waiter {
    String getName();

    int getEfficiency();

    boolean canWork();

    TakenOrders takenOrders();


    void work();
}
