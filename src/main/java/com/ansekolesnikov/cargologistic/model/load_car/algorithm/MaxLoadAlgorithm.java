package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class MaxLoadAlgorithm implements AlgorithmLoad{
    @Override
    public void load(Car car, CargoPackage cargoPackage) {
        new LoadPackageInCar().loadPackageInCar(car, cargoPackage);
    }
}
