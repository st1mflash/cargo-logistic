package com.ansekolesnikov.cargologistic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CargoCarTest {


    @Test
    void getCargoScheme() {
        CargoCar cargoCar = new CargoCar();
        cargoCar.initCargoSchemeFromString("5555500000000000000000000000000000000000");
        String expectedScheme =
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+55555 +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, cargoCar.getCargoScheme());

        cargoCar.initCargoSchemeFromString("5555504444007777007770000000000000000000");
        expectedScheme =
                        "+      +\n" +
                        "+      +\n" +
                        "+777   +\n" +
                        "+7777  +\n" +
                        "+4444  +\n" +
                        "+55555 +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, cargoCar.getCargoScheme());

        cargoCar.initCargoSchemeFromString("0000000000000000000000000000000000000000");
        expectedScheme =
                "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "+      +\n" +
                        "++++++++\n";
        assertEquals(expectedScheme, cargoCar.getCargoScheme());
    }

    @Test
    void checkPackageSupport() {
        CargoCar cargoCar = new CargoCar();
        cargoCar.initCargoSchemeFromString("2200000000000000000000000000000000000000");
        assertEquals(true, cargoCar.checkPackageSupport(0, 0, 3));
        assertEquals(true, cargoCar.checkPackageSupport(0, 0, 5));
        assertEquals(false, cargoCar.checkPackageSupport(1, 0, 5));
        assertEquals(true, cargoCar.checkPackageSupport(1, 0, 3));
    }

    @Test
    void getLoadPercent() {
        CargoCar cargoCar = new CargoCar();
        cargoCar.initCargoSchemeFromString("5555504444100000000000000000000000000");
        assertEquals(27, cargoCar.getLoadPercent());

        cargoCar.initCargoSchemeFromString("0000000000000000000000000000000000000");
        assertEquals(0, cargoCar.getLoadPercent());

        cargoCar.initCargoSchemeFromString("1111111111111111111111111111111111111");
        assertEquals(100, cargoCar.getLoadPercent());
    }

    @Test
    void initCargoSchemeFromString() {
        CargoCar cargoCar = new CargoCar();
        String cargoSchemeString = "5555500000000000000000000000000000000000";
        int[][] expectedArrCargoScheme = new int[][]{
                {5, 5, 5, 5, 5, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        cargoCar.initCargoSchemeFromString(cargoSchemeString);
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], cargoCar.getArrCargoScheme()[i][j]);
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
        cargoCar.initCargoSchemeFromString(cargoSchemeString);
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], cargoCar.getArrCargoScheme()[i][j]);
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
        cargoCar.initCargoSchemeFromString(cargoSchemeString);
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH; j++) {
                assertEquals(expectedArrCargoScheme[i][j], cargoCar.getArrCargoScheme()[i][j]);
            }
        }
    }
}