package Skeletons.barracksWars.core.factories;

import Skeletons.barracksWars.interfaces.Unit;
import Skeletons.barracksWars.interfaces.UnitFactory;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"Skeletons.barracksWars.models.units.";
	public Unit createUnit(String unitType) {
		Unit unit = null;
		try {
			Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			unit = (Unit) clazz.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			System.out.printf("%s%n",e.getMessage());
		}
		return unit;
	}
}