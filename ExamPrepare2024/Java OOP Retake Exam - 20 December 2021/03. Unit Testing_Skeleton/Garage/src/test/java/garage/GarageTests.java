package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car ford;
    private Car skoda;
    private Car nissan;

    @Before
    public void setUp(){
        this.garage = new Garage();
        ford = new Car("Ford", 300, 40000);
        skoda = new Car("Skoda", 280, 50000);
        nissan = new Car("Nissan", 260, 20000);

    }

    @Test(expected = IllegalArgumentException.class)
    public void  testAddCarShouldThrow(){
       Garage garage = new Garage();
       garage.addCar(null);
    }
    @Test
    public void testAddCarSuccessfully(){
        garage.addCar(ford);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(skoda);
        Assert.assertEquals(2,garage.getCount());
    }
    @Test
    public void testGetCarsSuccessfully(){
        garage.addCar(ford);
        List<Car> carsInGarage = garage.getCars();
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals(ford.getBrand(),carsInGarage.get(0).getBrand());
    }
    @Test
    public void  testGetFastestCar(){
        garage.addCar(ford);
        garage.addCar(skoda);
        garage.addCar(nissan);
        List<Car> carsWithSpeedAboutValue = garage.findAllCarsWithMaxSpeedAbove(250);
        Assert.assertEquals(ford.getBrand(), carsWithSpeedAboutValue.get(0).getBrand());
    }
    @Test
    public void testGetMostExpensiveCar(){
        garage.addCar(ford);
        garage.addCar(skoda);
        garage.addCar(nissan);
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(skoda.getBrand(), mostExpensiveCar.getBrand());
    }
    @Test
    public void testFindAllCarsByBrand(){
        garage.addCar(ford);
        garage.addCar(ford);
        garage.addCar(nissan);
        List<Car> cars = garage.findAllCarsByBrand(ford.getBrand());
        Assert.assertEquals(2,cars.size());
    }
}