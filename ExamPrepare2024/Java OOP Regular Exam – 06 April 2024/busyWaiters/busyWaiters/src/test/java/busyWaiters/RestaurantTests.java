package busyWaiters;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTests {

    private static final int CAPACITY = 10;
    private static final String RESTAURANT_NAME = "TestRestaurant";
    private static final String WAITER_NAME = "Waiter";

    private FullTimeWaiter waiter;
    private Restaurant restaurant;

    @Before
    public void Setup() {
        restaurant = new Restaurant(RESTAURANT_NAME, CAPACITY);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(RESTAURANT_NAME, restaurant.getName());
        Assert.assertEquals(CAPACITY, restaurant.getCapacity());
        Assert.assertTrue(restaurant.getWaiters().isEmpty());
        Assert.assertEquals(0, restaurant.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRestaurantShouldBeNegative() {
        new Restaurant(RESTAURANT_NAME, -10);
    }

    @Test(expected = NullPointerException.class)
    public void testRestaurantShouldWhenNameIsWhitespaces() {
        new Restaurant("       ", CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void testRestaurantShouldWhenNameNull() {
        new Restaurant(null, CAPACITY);
    }

    @Test
    public void testAddFullTimeWaiter() {
        FullTimeWaiter waiter1 = new FullTimeWaiter("Waiter1", 10);
        FullTimeWaiter waiter2 = new FullTimeWaiter("Waiter2", 5);

        restaurant.addFullTimeWaiter(waiter1);
        Assert.assertEquals(1, restaurant.getCount());
        Assert.assertTrue(restaurant.getWaiters().contains(waiter1));

        restaurant.addFullTimeWaiter(waiter2);
        Assert.assertEquals(2, restaurant.getCount());
        Assert.assertTrue(restaurant.getWaiters().contains(waiter2));


    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFullTimeWaiterCapacity() {
        FullTimeWaiter waiter1 = new FullTimeWaiter("Waiter1", 10);
        FullTimeWaiter waiter2 = new FullTimeWaiter("Waiter2", 5);

        for (int i = 0; i < CAPACITY; i++) {
            restaurant.addFullTimeWaiter(new FullTimeWaiter("Waiter" + i, CAPACITY));

        }
        restaurant.addFullTimeWaiter(new FullTimeWaiter("WaiterOverflow", 5));
    }

    @Test
    public void testRemoveFullTimeWaiter() {
        FullTimeWaiter waiter1 = new FullTimeWaiter("Waiter1", 10);
        FullTimeWaiter waiter2 = new FullTimeWaiter("Waiter2", 5);


        restaurant.addFullTimeWaiter(waiter1);
        restaurant.addFullTimeWaiter(waiter2);

        Assert.assertTrue(restaurant.removeFullTimeWaiter("Waiter1"));
        Assert.assertFalse(restaurant.getWaiters().contains(waiter1));
        Assert.assertEquals(1, restaurant.getCount());
    }

    @Test
    public void testGetWaiters(){
        List<FullTimeWaiter> waiters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FullTimeWaiter waiter = new FullTimeWaiter("Waiter" + i, CAPACITY);
            waiters.add(waiter);
            restaurant.addFullTimeWaiter(waiter);
        }
        Assert.assertEquals(waiters.size(), restaurant.getCount());
        Assert.assertEquals(waiters,new ArrayList<>(restaurant.getWaiters()));
    }
}
