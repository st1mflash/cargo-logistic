package com.ansekolesnikov.cargologistic.model.car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    @Test
    void testGetCargo() {
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