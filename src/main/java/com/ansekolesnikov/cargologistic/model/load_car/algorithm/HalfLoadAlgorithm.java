package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackageInCar;

public class HalfLoadAlgorithm implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (new CarUtils().calcPercentLoad(car) + (pack.getType() * 100) / (Car.WIDTH * Car.HEIGHT) <= 50) {
            new LoadPackageInCar().loadPackageInCar(car, pack);
        }
    }
}
