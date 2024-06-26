package com.ansekolesnikov.cargologistic.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmHalf implements ILoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (car.calcPercentLoad() + (pack.getScheme().replaceAll("0", "").length() * 100) / (car.getWidth() * car.getHeight()) <= 50) {
            car.loadPack(pack);
        }
    }
}
