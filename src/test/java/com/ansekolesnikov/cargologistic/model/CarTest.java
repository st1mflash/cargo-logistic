package com.ansekolesnikov.cargologistic.model;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfo;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {


    @Test
    void getCargoScheme() {
        Car car = new Car();
        car.initCargoFromString("5555500000000000000000000000000000000000");
        String expectedScheme =
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+55555 +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, new CarStringInfo().getCargo(car));

        car.initCargoFromString("5555504444007777007770000000000000000000");
        expectedScheme =
                        "+      +\n" +
                        "+      +\n" +
                        "+777   +\n" +
                        "+7777  +\n" +
                        "+4444  +\n" +
                        "+55555 +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, new CarStringInfo().getCargo(car));

        car.initCargoFromString("0000000000000000000000000000000000000000");
        expectedScheme =
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, new CarStringInfo().getCargo(car));
    }

    @Test
    void checkPackageSupport() {
        Car car = new Car();
        car.initCargoFromString("2200000000000000000000000000000000000000");
        //assertEquals(true, new CarUtils().isCanLoadPackageOnCargoPosition(car, 0, 0, 3));
        //assertEquals(true, new CarUtils().isCanLoadPackageOnCargoPosition(car,0, 0, 5));
        //assertEquals(false, new CarUtils().isCanLoadPackageOnCargoPosition(car, 1, 0, 5));
        //assertEquals(true, new CarUtils().isCanLoadPackageOnCargoPosition(car, 1, 0, 3));
    }

    @Test
    void getLoadPercent() {
        Car car = new Car();
        car.initCargoFromString("5555504444100000000000000000000000000");
        assertEquals(27, new CarUtils().calcPercentLoad(car));

        car.initCargoFromString("0000000000000000000000000000000000000");
        assertEquals(0, new CarUtils().calcPercentLoad(car));

        car.initCargoFromString("1111111111111111111111111111111111111");
        assertEquals(100, new CarUtils().calcPercentLoad(car));
    }

    @Test
    void initCargoSchemeFromString() {
        Car car = new Car();
        String cargoSchemeString = "5555500000000000000000000000000000000000";
        int[][] expectedArrCargoScheme = new int[][]{
                {5, 5, 5, 5, 5, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        car.initCargoFromString(cargoSchemeString);
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], car.getCargo()[i][j]);
            }
        }

        cargoSchemeString = "5555504444007777007770000000000000000000";
        expectedArrCargoScheme = new int[][]{
                {5, 5, 5, 5, 5, 0},
                {4, 4, 4, 4, 0, 0},
                {7, 7, 7, 7, 0, 0},
                {7, 7, 7, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        car.initCargoFromString(cargoSchemeString);
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], car.getCargo()[i][j]);
            }
        }

        cargoSchemeString = "0000000000000000000000000000000000000000";
        expectedArrCargoScheme = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        car.initCargoFromString(cargoSchemeString);
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], car.getCargo()[i][j]);
            }
        }
    }
}