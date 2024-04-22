package com.ansekolesnikov.cargologistic.model.car_load.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.car_load.LoadPackageToCar;

public class AlgorithmLoadMax implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        new LoadPackageToCar().loadPackageInCar(cargoCar, cargoPackage);
    }
}
