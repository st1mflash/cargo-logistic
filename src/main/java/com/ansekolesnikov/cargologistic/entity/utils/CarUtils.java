package com.ansekolesnikov.cargologistic.entity.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
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

    public int calculateCountPackInCarByCode(Car car, Character code) {
        PackModel packModel = packModelDao.findByCode(code);
        int packSize = packModel.getScheme().replaceAll("0", "").length();
        return Arrays.deepToString(car.getCargo()).replaceAll("[^" + code + "]", "").length() / packSize;
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
