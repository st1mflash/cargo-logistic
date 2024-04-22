package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.HalfLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.MaxLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.TypeLoadAlgorithm;

public class LoadCar {
    public void loadPackage(String algorithm, CargoCar cargoCar, CargoPackage cargoPackage) {
        switch (algorithm) {
            case "max":
                new MaxLoadAlgorithm().load(cargoCar, cargoPackage);
                break;
            case "half":
                new HalfLoadAlgorithm().load(cargoCar, cargoPackage);
                break;
            case "type":
                new TypeLoadAlgorithm().load(cargoCar, cargoPackage);
                break;
            default:
                break;
        }
    }
}
