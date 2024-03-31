package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut astronaut1;
    private Astronaut astronaut2;
    private Astronaut astronaut3;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("Starship", 2);
        Astronaut astronaut1 = new Astronaut("John Doe", 123);
        Astronaut astronaut2 = new Astronaut("Jane Smith", 456);
        Astronaut astronaut3 = new Astronaut("Alice", 789);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidCapacity() {
        new Spaceship("Starship", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetInvalidSpaceshipName() {
        new Spaceship(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetInvalidSWithEmptyString() {
        new Spaceship("     ", 3);
    }

    @Test
    public void testRemoveAstronaut() {
        Spaceship spaceship = new Spaceship("Starship", 3);
        Astronaut astronaut = new Astronaut("John Doe", 123);

        spaceship.add(astronaut);
        boolean isRemoved = spaceship.remove("John Doe");

        Assert.assertTrue(isRemoved);
        Assert.assertEquals(0, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautToFullSpaceship() {
        Spaceship spaceship = new Spaceship("Starship", 1);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateAstronaut() {
        Spaceship spaceship = new Spaceship("Starship", 3);
        Astronaut astronaut1 = new Astronaut("John Doe", 123);
        Astronaut astronaut2 = new Astronaut("John Doe", 456);

        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Starship", this.spaceship.getName());
    }
}
