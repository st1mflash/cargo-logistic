package com.ansekolesnikov.cargologistic.entity.car.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.load.algorithm.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.entity.load.algorithm.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.entity.load.algorithm.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
@Component
public class CarUtils {
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private LoadAlgorithmMax loadAlgorithmMax;
    @Autowired
    private LoadAlgorithmHalf loadAlgorithmHalf;
    @Autowired
    private LoadAlgorithmType loadAlgorithmType;
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

    public int calculateCountPackInCarByCode(Car car, Character code) {

        return 0;
    }

    public void loadPackToCar(Car car, Pack pack, AlgorithmEnum algorithm) {
        switch (algorithm) {
            case MAX:
                loadAlgorithmMax.load(car, pack);
                break;
            case HALF:
                loadAlgorithmHalf.load(car, pack);
                break;
            case TYPE:
                loadAlgorithmType.load(car, pack);
                break;
            default:
                break;
        }
    }
}
