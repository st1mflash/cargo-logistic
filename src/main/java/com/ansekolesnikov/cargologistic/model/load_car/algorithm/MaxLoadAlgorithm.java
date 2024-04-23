package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class MaxLoadAlgorithm implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        new LoadPackageInCar().loadPackageInCar(car, pack);
    }
}
