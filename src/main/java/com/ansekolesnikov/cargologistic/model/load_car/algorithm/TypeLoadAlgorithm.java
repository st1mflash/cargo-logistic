package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class TypeLoadAlgorithm implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        if (cargoCar.getArrCarScheme()[0][0] == cargoPackage.getType()
                || cargoCar.getArrCarScheme()[0][0] == 0) {
            new LoadPackageInCar().loadPackageInCar(cargoCar, cargoPackage);
        }
    }
}
