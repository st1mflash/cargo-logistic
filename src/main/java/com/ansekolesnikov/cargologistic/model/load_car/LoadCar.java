package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.HalfLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.MaxLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.TypeLoadAlgorithm;

public class LoadCar {
    public void loadPackage(String algorithm, Car car, CargoPackage cargoPackage) {
        switch (algorithm) {
            case "max":
                new MaxLoadAlgorithm().load(car, cargoPackage);
                break;
            case "half":
                new HalfLoadAlgorithm().load(car, cargoPackage);
                break;
            case "type":
                new TypeLoadAlgorithm().load(car, cargoPackage);
                break;
            default:
                break;
        }
    }
}
