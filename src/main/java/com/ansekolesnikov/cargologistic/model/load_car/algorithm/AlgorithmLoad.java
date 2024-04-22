package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoPackage;

public interface AlgorithmLoad {
    void load(Car car, CargoPackage cargoPackage);
}
