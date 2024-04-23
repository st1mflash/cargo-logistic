package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class TypeLoadAlgorithm implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (car.getCargo()[0][0] == pack.getType()
                || car.getCargo()[0][0] == 0) {
            new LoadPackageInCar().loadPackageInCar(car, pack);
        }
    }
}
