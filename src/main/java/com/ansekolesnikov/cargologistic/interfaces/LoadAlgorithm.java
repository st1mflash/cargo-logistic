package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;

public interface LoadAlgorithm {
    void load(Car car, Pack pack);
}
