package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private House house;

    @Before
    public void setUp() {
        house = new House("TestHouse", 5);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseWithInvalidCapacity() {
        new House("House1", -4);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidName() {
        new House(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidNameEmpty() {
        new House("", 5);
    }

    @Test
    public void testCreateHouse() {
        House house = new House("House1", 10);
        Assert.assertEquals("House1", house.getName());
        Assert.assertEquals(10, house.getCapacity());
    }

    //adCat
    @Test
    public void testAddCat() {
        House house1 = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Assert.assertEquals(0, house1.getCount());

        house1.addCat(mike);
        Assert.assertEquals(1, house1.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrowExceptionFilledHouse() {
        House house = new House("House1", 1);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());

        Cat betty = new Cat("Betty");
        house.addCat(betty);
    }
    //Remove

    @Test
    public void testRemoveCat() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        house.addCat(betty);
        Assert.assertEquals(2, house.getCount());

        house.removeCat("Betty");
        Assert.assertEquals(1, house.getCount());
        house.removeCat("Mike");
        Assert.assertEquals(0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingCat() {
        House house = new House("House1", 10);
        house.removeCat("Ivan");
    }

    //catForSale
    @Test
    public void testCatForSale_Valid() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat soldCat = house.catForSale("Mike");
        Assert.assertFalse(soldCat.isHungry());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSale_InvalidName(){
        House house = new House("House1", 10);
        house.catForSale("Ivan");
    }
    //statistics
    @Test
    public void testStatistics(){
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        house.addCat(betty);
        Assert.assertEquals("The cat Mike, Betty is in the house House1!", house.statistics());
    }
}
