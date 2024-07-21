package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {

    private Farm farm;
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;

    @Before
    public void setUp() {
        this.farm = new Farm("FarmVillage", 50);
        this.animal1 = new Animal("Cow", 20);
        this.animal2 = new Animal("Sheep", 30);
        this.animal3 = new Animal("Wolf", 30);
    }

    @Test
    public void test_Constructor_IsValid() {
        assertEquals("FarmVillage", farm.getName());
        assertEquals(50, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_InvalidCapacity() {
        new Farm("FarmVillage", -50);
    }

    @Test(expected = NullPointerException.class)
    public void test_InvalidName_Farm() {
        new Farm(null, 20);
    }

    @Test(expected = NullPointerException.class)
    public void test_Invalid_With_EmptyString() {
        new Farm("     ", 10);
    }

    @Test
    public void test_add_Animal_Successfully() {
        farm.add(animal1);
        assertEquals(1, farm.getCount());
        farm.add(animal2);
        assertEquals(2, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_Animal_Should_Return_Full_Capacity() {
        Farm farm1 = new Farm("test", 1);
        farm1.add(animal1);
        farm1.add(animal2);
        farm1.add(animal3);


    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_Animal_Should_Return_Duplicate() {
        farm.add(animal1);
        farm.add(animal1);
        assertEquals(1, animal1.getType());
    }

    @Test
    public void test_Remove_Animal_Should_Successfully() {
        farm.add(animal1);
        assertEquals(1, farm.getCount());
        farm.remove(animal1.getType());
        assertEquals(0, farm.getCount());
    }

    @Test
    public void test_Remove_Animal_NotInFarm() {
        assertFalse(farm.remove(animal1.getType()));
    }

    @Test
    public void test_Check_Energy_Animal() {

        farm.add(animal1);
        assertEquals(20, animal1.getEnergy(), 0.001);


    }
}
