package com.ansekolesnikov.cargologistic.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILoadAlgorithm;
import com.ansekolesnikov.cargologistic.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LoadAlgorithmType implements ILoadAlgorithm {
    private final CarService carService;

    @Override
    public void load(Car car, Pack pack) {
        if (Objects.equals(car.getCargo()[0][0], String.valueOf(pack.getCode()))
                || Objects.equals(car.getCargo()[0][0], "0")) {
            carService.loadPack(car, pack);
        }
    }
}
