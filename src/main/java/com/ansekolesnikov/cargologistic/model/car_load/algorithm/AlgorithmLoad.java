package com.ansekolesnikov.cargologistic.model.car_load.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;

public interface AlgorithmLoad {
    void load(CargoCar cargoCar, CargoPackage cargoPackage);
}
