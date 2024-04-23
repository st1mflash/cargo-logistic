package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.HalfLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.MaxLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.TypeLoadAlgorithm;

public class LoadCar {
    public void loadPackage(String algorithm, Car car, Pack pack) {
        switch (algorithm) {
            case "max":
                new MaxLoadAlgorithm().load(car, pack);
                break;
            case "half":
                new HalfLoadAlgorithm().load(car, pack);
                break;
            case "type":
                new TypeLoadAlgorithm().load(car, pack);
                break;
            default:
                break;
        }
    }
}
