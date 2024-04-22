package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class HalfLoadAlgorithm implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        if (cargoCar.getLoadPercent() + (cargoPackage.getType() * 100) / (CargoCar.WIDTH * CargoCar.HEIGHT) <= 50) {
            new LoadPackageInCar().loadPackageInCar(cargoCar, cargoPackage);
        }
    }
}
