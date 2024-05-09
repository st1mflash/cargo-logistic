package com.ansekolesnikov.cargologistic.model.car;

import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

import java.util.Arrays;
import java.util.Objects;

public class CarUtils {
    public int calcPercentLoad(Car car) {
        String[][] cargo = car.getCargo();
        int countFilledPoints = 0;

        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                if (!Objects.equals(cargo[i][j], "0")) {
                    countFilledPoints++;
                }
            }
        }
        return (countFilledPoints * 100) / (Car.WIDTH * Car.HEIGHT);
    }

    public boolean isCanLoadPackOnCargoPosition(Car car, Pack pack, int startHeightPos, int startWidthPos) {
        String[][] cargo = car.getCargo();
        int packWidth = pack.getWidth();

        if (startHeightPos == 0) {
            return true;
        } else {
            int sumPackWidthSupport = 0;
            for (int j = startWidthPos; j < startWidthPos + packWidth; j++) {
                if (!Objects.equals(cargo[startHeightPos - 1][j], "0")) {
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

    public void loadPackToCar(Car car, Pack pack, String algorithm) {
        switch (algorithm) {
            case "max":
                new LoadAlgorithmMax().load(car, pack);
                break;
            case "half":
                new LoadAlgorithmHalf().load(car, pack);
                break;
            case "type":
                new LoadAlgorithmType().load(car, pack);
                break;
            default:
                break;
        }
    }
}
