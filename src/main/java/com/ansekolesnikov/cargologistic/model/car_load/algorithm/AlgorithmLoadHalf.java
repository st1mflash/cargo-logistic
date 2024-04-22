package com.ansekolesnikov.cargologistic.model.car_load.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.car_load.LoadPackageToCar;

public class AlgorithmLoadHalf implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        if (cargoCar.getLoadPercent() + (cargoPackage.getType() * 100) / (CargoCar.WIDTH * CargoCar.HEIGHT) <= 50) {
            new LoadPackageToCar().loadPackageInCar(cargoCar, cargoPackage);
        }
    }
}
