package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.load.LoadPackUtils;

public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (new CarUtils().calcPercentLoad(car) + (pack.calculateElements() * 100) / (car.getCargoWidthModel() * car.getCargoHeightModel()) <= 50) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}