package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.load.LoadPackUtils;

public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, PackModel packModel) {
        if (new CarUtils().calcPercentLoad(car) + (packModel.calculateElements() * 100) / (car.getCargoWidthModel() * car.getCargoHeightModel()) <= 50) {
            new LoadPackUtils().loadPackInCar(car, packModel);
        }
    }
}
