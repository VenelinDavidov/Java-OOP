package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.orders.TakenOrders;
import restaurant.models.waiter.Waiter;

import java.util.Collection;

public class WorkingImpl implements Working {


    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {

        // implement methods
        // WorkingImpl class


        Collection<String> ordersForClient = client.getClientOrders();

//        for (Waiter waiter : waiters) {
//            if (waiter.canWork()) {
//                waiter.work();
//                Collection<String> orders = ((ClientImpl) client).getClientOrders();
//                for (String order : orders) {
//                    waiter.takenOrders().getOrdersList().add(order);
//                }
//                ((ClientImpl) client).getClientOrders().clear();
//            }
//        }
//    }
//}

        for (Waiter waiter : waiters) {
            while (waiter.canWork() && ordersForClient.iterator().hasNext()) {
                waiter.work();
                String currentOrderClient = ordersForClient.iterator().next();
                waiter.takenOrders().getOrdersList().add(currentOrderClient);

                ordersForClient.remove(currentOrderClient);
            }
        }
    }
}
