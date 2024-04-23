package com.ansekolesnikov.cargologistic.model.car;

import com.ansekolesnikov.cargologistic.model.CargoPackage;

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
    public boolean isCanLoadPackageOnCargoPosition(Car car, CargoPackage cargoPackage, int posHeight, int posWidth) {
        int[][] cargo = car.getCargo();
        int packageWidth = cargoPackage.getWidth();

        if (posHeight == 0) {
            return true;
        } else {
            int sumPackageSupport = 0;
            for (int j = posWidth; j < posWidth + packageWidth; j++) {
                if (cargo[posHeight - 1][j] != 0) {
                    sumPackageSupport = sumPackageSupport + 1;
                }
            }
            return sumPackageSupport > packageWidth / 2;
        }
    }
}
