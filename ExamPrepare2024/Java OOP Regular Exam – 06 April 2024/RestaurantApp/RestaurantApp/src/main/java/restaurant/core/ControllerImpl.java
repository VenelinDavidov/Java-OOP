package restaurant.core;

import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.WaiterRepository;

import java.util.Collection;

import static restaurant.common.ConstantMessages.*;
import static restaurant.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private final ClientRepository clientRepository;
    private final WaiterRepository waiterRepository;
    private int TOTAL_COUNT_SEVERAL_CLIENTS;

    //constructor


    public ControllerImpl() {
        this.clientRepository = new ClientRepository();
        this.waiterRepository = new WaiterRepository();
    }

    //methods
    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter;
        switch (type) {
            case "FullTimeWaiter":
                waiter = new FullTimeWaiter(waiterName);
                break;
            case "HalfTimeWaiter":
                waiter = new HalfTimeWaiter(waiterName);
                break;
            default:
                throw new IllegalArgumentException(WAITER_INVALID_TYPE);
        }
        waiterRepository.add(waiter);
        return String.format(WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName); // Create a client

        for (String order : orders) {
            client.getClientOrders().add(order);
        }
        clientRepository.add(client);
        return String.format(CLIENT_ADDED, clientName);
    }

    //RemoveWaiter Command
    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = waiterRepository.byName(waiterName);
        if (waiter == null) {
            String message = String.format(WAITER_DOES_NOT_EXIST, waiterName);
            throw new IllegalArgumentException(message);
        }
        waiterRepository.remove(waiter);
        return String.format(WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        Client client = clientRepository.byName(clientName);

        if (client == null) {
            String message = String.format(CLIENT_DOES_NOT_EXIST, clientName);
            throw new IllegalArgumentException(message);
        }

        clientRepository.remove(client);

        return String.format(CLIENT_REMOVE, clientName);
    }

    //StartWorking Command
    @Override
    public String startWorking(String clientName) {

        Collection<Waiter> waiters = waiterRepository.getCollection();

        if (waiters.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_WAITERS);
        }
        Client client = clientRepository.byName(clientName);

        Working working = new WorkingImpl();
        working.takingOrders(client, waiters);

        TOTAL_COUNT_SEVERAL_CLIENTS++;
        return String.format(ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_CLIENTS_COUNT, TOTAL_COUNT_SEVERAL_CLIENTS))
                .append(System.lineSeparator());
        sb.append(FINAL_WAITERS_STATISTICS)
                .append(System.lineSeparator());

        //интерираме по сервитьоте
        Collection<Waiter> waiters = waiterRepository.getCollection();
        for (Waiter waiter : waiters) {
            sb.append(String.format(FINAL_WAITER_NAME, waiter.getName()))
                    .append(System.lineSeparator());
            sb.append(String.format(FINAL_WAITER_EFFICIENCY, waiter.getEfficiency()))
                    .append(System.lineSeparator());

            if (waiter.takenOrders().getOrdersList().isEmpty()) {
                sb.append(String.format(FINAL_WAITER_ORDERS, "None"))
                        .append(System.lineSeparator());
            } else {
                sb.append(String.format(FINAL_WAITER_ORDERS,
                        String.join(FINAL_WAITER_ORDERS_DELIMITER, waiter.takenOrders().getOrdersList())))
                        .append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
