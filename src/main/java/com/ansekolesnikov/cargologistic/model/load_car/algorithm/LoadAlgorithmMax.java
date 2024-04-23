package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackUtils;

public class LoadAlgorithmMax implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        new LoadPackUtils().loadPackInCar(car, pack);
    }
}
