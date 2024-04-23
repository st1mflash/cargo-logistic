package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackUtils;

public class LoadAlgorithmType implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (car.getCargo()[0][0] == pack.getType()
                || car.getCargo()[0][0] == 0) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
