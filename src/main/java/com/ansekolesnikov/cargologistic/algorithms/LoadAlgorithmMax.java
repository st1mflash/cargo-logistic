package com.ansekolesnikov.cargologistic.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILoadAlgorithm;
import com.ansekolesnikov.cargologistic.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadAlgorithmMax implements ILoadAlgorithm {
    private final CarService carService;

    @Override
    public void load(Car car, Pack pack) {
        carService.loadPack(car, pack);
    }
}
