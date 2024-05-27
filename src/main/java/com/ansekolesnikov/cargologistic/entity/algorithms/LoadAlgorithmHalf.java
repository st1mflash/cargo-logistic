package com.ansekolesnikov.cargologistic.entity.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.entity.utils.LoadPackUtils;
import com.ansekolesnikov.cargologistic.interfaces.LoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (car.calcPercentLoad() + (pack.calculateCountElements() * 100) / (car.getCargoWidthModel() * car.getCargoHeightModel()) <= 50) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
