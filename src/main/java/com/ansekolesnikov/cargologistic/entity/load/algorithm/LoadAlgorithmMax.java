package com.ansekolesnikov.cargologistic.entity.load.algorithm;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.load.LoadPackUtils;

public class LoadAlgorithmMax implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        new LoadPackUtils().loadPackInCar(car, pack);
    }
}
