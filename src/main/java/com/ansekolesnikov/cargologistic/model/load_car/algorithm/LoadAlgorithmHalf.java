package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackUtils;

public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (new CarUtils().calcPercentLoad(car) + (pack.calculateElements() * 100) / (Car.WIDTH * Car.HEIGHT) <= 50) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
