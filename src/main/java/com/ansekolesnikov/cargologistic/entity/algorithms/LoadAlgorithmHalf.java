package com.ansekolesnikov.cargologistic.entity.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.LoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (car.calcPercentLoad() + (pack.calculateCountElements() * 100) / (car.getWidth() * car.getHeight()) <= 50) {
            car.loadPack(pack);
        }
    }
}
