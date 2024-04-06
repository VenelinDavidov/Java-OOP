package restaurant.models.orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TakenOrdersImpl implements TakenOrders {
    Collection<String> ordersList;


    //Constructor


    public TakenOrdersImpl() {
        this.ordersList = new ArrayList<>();
    }

    @Override
    public Collection<String> getOrdersList() {
        return ordersList;
    }
}
