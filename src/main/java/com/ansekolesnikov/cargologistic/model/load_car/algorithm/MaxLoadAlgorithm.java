package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class MaxLoadAlgorithm implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        new LoadPackageInCar().loadPackageInCar(cargoCar, cargoPackage);
    }
}
