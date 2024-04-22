package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class HalfLoadAlgorithm implements AlgorithmLoad{
    @Override
    public void load(Car car, CargoPackage cargoPackage) {
        if (car.getLoadPercent() + (cargoPackage.getType() * 100) / (Car.WIDTH * Car.HEIGHT) <= 50) {
            new LoadPackageInCar().loadPackageInCar(car, cargoPackage);
        }
    }
}
