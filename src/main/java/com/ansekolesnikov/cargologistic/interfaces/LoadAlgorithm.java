package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;

public interface LoadAlgorithm {
    void load(Car car, Pack pack);
}
