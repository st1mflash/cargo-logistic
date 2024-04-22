package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;

public interface AlgorithmLoad {
    void load(CargoCar cargoCar, CargoPackage cargoPackage);
}
