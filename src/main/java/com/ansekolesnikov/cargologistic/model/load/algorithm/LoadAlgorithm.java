package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

public interface LoadAlgorithm {
    void load(Car car, Pack pack);
}