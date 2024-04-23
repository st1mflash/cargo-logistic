package com.ansekolesnikov.cargologistic.model.car;

import com.ansekolesnikov.cargologistic.model.pack.Pack;

import java.util.Arrays;

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

    public boolean isCanLoadPackOnCargoPosition(Car car, Pack pack, int startHeightPos, int startWidthPos) {
        int[][] cargo = car.getCargo();
        int packWidth = pack.getWidth();

        if (startHeightPos == 0) {
            return true;
        } else {
            int sumPackWidthSupport = 0;
            for (int j = startWidthPos; j < startWidthPos + packWidth; j++) {
                if (cargo[startHeightPos - 1][j] != 0) {
                    sumPackWidthSupport = sumPackWidthSupport + 1;
                }
            }
            return sumPackWidthSupport > packWidth / 2;
        }
    }

    public int calcCountThisTypePackOnCar(Car car, int cargoPackageType) {
        String loadToString = Arrays.deepToString(car.getCargo()).replaceAll("\\D", "");
        return (loadToString.length() - (loadToString.replace(Integer.toString(cargoPackageType), "").length())) / cargoPackageType;
    }
}
