package com.ansekolesnikov.cargologistic.model.car.utils;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.load.algorithm.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.model.load.algorithm.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.model.load.algorithm.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;

import java.util.Arrays;
import java.util.Objects;

public class CarUtils {
    public int calcPercentLoad(Car car) {
        String[][] cargo = car.getCargo();
        int countFilledPoints = 0;

        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel(); j++) {
                if (!Objects.equals(cargo[i][j], "0")) {
                    countFilledPoints++;
                }
            }
        }
        return (countFilledPoints * 100) / (car.getCargoWidthModel() * car.getCargoHeightModel());
    }

    public int calcCountThisTypePackOnCar(Car car, int cargoPackageType) {
        String loadToString = Arrays.deepToString(car.getCargo()).replaceAll("\\D", "");
        return (loadToString.length() - (loadToString.replace(Integer.toString(cargoPackageType), "").length())) / cargoPackageType;
    }

    public void loadPackToCar(Car car, Pack pack, AlgorithmEnum algorithm) {
        switch (algorithm) {
            case MAX:
                new LoadAlgorithmMax().load(car, pack);
                break;
            case HALF:
                new LoadAlgorithmHalf().load(car, pack);
                break;
            case TYPE:
                new LoadAlgorithmType().load(car, pack);
                break;
            default:
                break;
        }
    }
}
