package restaurant.models.waiter;

//TODO
public class HalfTimeWaiter extends BaseWaiter {
    public static int HALF_TIME_WAITER = 4;

    public HalfTimeWaiter(String name) {
        super(name, HALF_TIME_WAITER);
    }

    @Override
    public void work() {

        if (getEfficiency() >= 2) {
            setEfficiency(getEfficiency() - 2);

        } else {
            HALF_TIME_WAITER = 0;
        }
    }


}
