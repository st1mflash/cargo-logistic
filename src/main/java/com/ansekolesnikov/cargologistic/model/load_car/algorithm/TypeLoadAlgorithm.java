package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class TypeLoadAlgorithm implements LoadAlgorithm {
    @Override
    public void load(Car car, CargoPackage cargoPackage) {
        if (car.getCargo()[0][0] == cargoPackage.getType()
                || car.getCargo()[0][0] == 0) {
            new LoadPackageInCar().loadPackageInCar(car, cargoPackage);
        }
    }
}
