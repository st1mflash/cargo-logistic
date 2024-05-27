package com.ansekolesnikov.cargologistic.entity.car;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.utils.CarUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarUtilsTest {
    /*
    @Test
    void testCalcPercentLoad() {
        Car car = new Car();
        CarUtils carUtils = new CarUtils();

        int percentLoad = carUtils.calcPercentLoad(car);
        assertEquals(0, percentLoad);

        car.initCargoFromString("555550555550555550555550555550555550");
        percentLoad = carUtils.calcPercentLoad(car);
        assertEquals(83, percentLoad);

        car.initCargoFromString("5555514444223330000000000000000000000000");
        percentLoad = carUtils.calcPercentLoad(car);
        assertEquals(41, percentLoad);

        car.initCargoFromString("111111111111111111111111111111111111");
        percentLoad = carUtils.calcPercentLoad(car);
        assertEquals(100, percentLoad);
    }

     */

    @Test
    void testIsCanLoadPackOnCargoPosition() {
        Car car = new Car();
        CarUtils carUtils = new CarUtils();

        /*
        Pack pack = new Pack(2);
        boolean canLoad = carUtils.isCanLoadPackOnCargoPosition(car, pack, 0, 0);
        assertTrue(canLoad);

        pack = new Pack(4);
        car.initCargoFromString("100000000000000000000000000000000000");
        canLoad = carUtils.isCanLoadPackOnCargoPosition(car, pack, 1, 0);
        assertFalse(canLoad);

        pack = new Pack(4);
        car.initCargoFromString("110000000000000000000000000000000000");
        canLoad = carUtils.isCanLoadPackOnCargoPosition(car, pack, 1, 0);
        assertFalse(canLoad);

        pack = new Pack(4);
        car.initCargoFromString("111000000000000000000000000000000000");
        canLoad = carUtils.isCanLoadPackOnCargoPosition(car, pack, 1, 0);
        assertTrue(canLoad);
        */
    }

    /*
    @Test
    void testCalcCountThisTypePackOnCar() {
        Car car = new Car();
        CarUtils carUtils = new CarUtils();

        char cargoPackageType = '1';
        car.initCargoFromString("111000000000000000000000000000000000");
        int count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(3, count);

        cargoPackageType = '2';
        car.initCargoFromString("111000000000000000000000000000000000");
        count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(0, count);

        car.initCargoFromString("5555514444223330000000000000000000000000");
        cargoPackageType = '5';
        count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(1, count);

        cargoPackageType = '4';
        count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(1, count);

        cargoPackageType = '2';
        count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(1, count);

        cargoPackageType = '3';
        count = carUtils.calculateCountPackInCarByCode(car, cargoPackageType);
        assertEquals(1, count);
    }
    */
}
