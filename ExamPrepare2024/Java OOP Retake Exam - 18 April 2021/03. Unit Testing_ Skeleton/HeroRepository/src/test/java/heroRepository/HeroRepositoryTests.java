package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;
    private Hero hero;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Veni", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHeroIsNull() {
        HeroRepository heroRepository1 = new HeroRepository();
        heroRepository1.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroDuplicateName() {
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }

    @Test
    public void testCreateHeroAdd() {
        Assert.assertEquals(0, heroRepository.getCount());
        this.heroRepository.create(this.hero);
        Assert.assertEquals(1, heroRepository.getCount());
        Hero hero1 = this.heroRepository.getHero("Veni");
        Assert.assertEquals(hero1.getName(), this.hero.getName());
        Assert.assertEquals(hero1.getLevel(), this.hero.getLevel());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithName() {
        this.heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithEmpty() {
        this.heroRepository.remove("    ");
    }

    @Test
    public void testRemoveSuccessfully() {
        Assert.assertEquals(0, this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        Assert.assertEquals(1, this.heroRepository.getCount());

        this.heroRepository.remove("Veni");
        Assert.assertEquals(0, this.heroRepository.getCount());
        Hero hero1 = this.heroRepository.getHero("Veni");
        Assert.assertNull(hero1);

    }

    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(new Hero("Superman", 10));
        heroRepository.create(new Hero("Batman", 15));
        heroRepository.create(new Hero("Wonder Woman", 5));

        Hero highestLevelHero = heroRepository.getHeroWithHighestLevel();
        Assert.assertNotNull(highestLevelHero);
        Assert.assertEquals("Batman", highestLevelHero.getName());
        Assert.assertEquals(15,highestLevelHero.getLevel() );

    }
    @Test(expected = UnsupportedOperationException.class)
    public  void testGetHero(){
        heroRepository.create(new Hero("Superman", 10));
        heroRepository.create(new Hero("Batman", 15));


       Collection<Hero> heroes = heroRepository.getHeroes();
       Assert.assertEquals(2, heroes.size());
       heroes.add(new Hero("Wonder Woman", 5));

    }

}
