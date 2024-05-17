package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.load.LoadPackUtils;

public class LoadAlgorithmMax implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        new LoadPackUtils().loadPackInCar(car, pack);
    }
}
