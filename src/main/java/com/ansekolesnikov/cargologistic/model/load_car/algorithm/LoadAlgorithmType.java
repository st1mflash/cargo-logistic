package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.LoadPackUtils;

import java.util.Objects;

public class LoadAlgorithmType implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (Objects.equals(car.getCargo()[0][0], pack.getType())
                || Objects.equals(car.getCargo()[0][0], "0")) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
