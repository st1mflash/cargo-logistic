package com.ansekolesnikov.cargologistic.model.car;

public class CarUtils {
    public int calcPercentLoad(Car car) {
        int[][] cargo = car.getCargo();
        int countFilledPoints = 0;

        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                if (cargo[i][j] != 0) {
                    countFilledPoints++;
                }
            }
        }
        return (countFilledPoints * 100) / (Car.WIDTH * Car.HEIGHT);
    }
}
