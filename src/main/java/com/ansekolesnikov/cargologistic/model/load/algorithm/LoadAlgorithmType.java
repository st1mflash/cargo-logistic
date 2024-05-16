package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.model.load.LoadPackUtils;

import java.util.Objects;

public class LoadAlgorithmType implements LoadAlgorithm {
    @Override
    public void load(Car car, PackModel packModel) {
        if (Objects.equals(car.getCargo()[0][0], packModel.getCode())
                || Objects.equals(car.getCargo()[0][0], "0")) {
            new LoadPackUtils().loadPackInCar(car, packModel);
        }
    }
}
