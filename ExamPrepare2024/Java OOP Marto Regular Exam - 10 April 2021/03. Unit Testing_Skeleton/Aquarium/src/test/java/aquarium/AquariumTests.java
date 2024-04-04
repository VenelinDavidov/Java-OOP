package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {

    @Test
    public void testAquariumInitialization() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        Assert.assertEquals("test_aquarium", aquarium.getName());
        Assert.assertEquals(10, aquarium.getCapacity());
        Assert.assertEquals(0, aquarium.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testAquariumShouldWhenNameNull() {
        new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testAquariumShouldWhenNameIsWhitespaces() {
        new Aquarium("    ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquariumShouldBeNegativeZeroCapacity() {
        new Aquarium("test_aquarium", -10);
    }

    @Test
    public void testAddFish() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aquarium.add(fish1);
        aquarium.add(fish2);
        Assert.assertEquals(2, aquarium.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquariumFull() {
        Aquarium aquarium = new Aquarium("test_aquarium", 1);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aquarium.add(fish1);
        aquarium.add(fish2);

    }

    @Test
    public void testRemoveFish() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aquarium.add(fish1);
        aquarium.add(fish2);
        Assert.assertEquals(2, aquarium.getCount());

        aquarium.remove("Fish1");
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFishShouldFaillNoSuchFish() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        aquarium.remove("test_name");
    }

    @Test
    public void testSellFishAsSold() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.sellFish("Fish1");
        Assert.assertFalse(fish1.isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldFailedNoSuchFishAdded() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);
        aquarium.sellFish("test_name");
    }



    @Test
    public void testGetInfo() {
        Aquarium aquarium = new Aquarium("test_aquarium", 10);

        aquarium.add(new Fish("fish1"));
        aquarium.add(new Fish("fish2"));

        String expected ="Fish available at test_aquarium: fish1, fish2";
        Assert.assertEquals(expected,aquarium.report());
    }
}

