package com.ansekolesnikov.cargologistic.model.car_load.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.car_load.LoadPackageToCar;

public class AlgorithmLoadType implements AlgorithmLoad{
    @Override
    public void load(CargoCar cargoCar, CargoPackage cargoPackage) {
        if (cargoCar.getArrCargoScheme()[0][0] == cargoPackage.getType()
                || cargoCar.getArrCargoScheme()[0][0] == 0) {
            new LoadPackageToCar().loadPackageInCar(cargoCar, cargoPackage);
        }
    }
}
