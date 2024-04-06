package restaurant.models.waiter;

//TODO
public class FullTimeWaiter extends BaseWaiter {

    public static int FULL_TIME_WAITER = 8;

    public FullTimeWaiter(String name) {
        super(name, FULL_TIME_WAITER);
    }

    @Override
    public void work() {
        if (getEfficiency() >= 1) {
            setEfficiency(getEfficiency() - 1);
        } else {
            FULL_TIME_WAITER = 0;
        }
    }
}
