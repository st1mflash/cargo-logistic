package com.ansekolesnikov.cargologistic.entity.load.algorithm;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.entity.load.LoadPackUtils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmHalf implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (new CarUtils().calcPercentLoad(car) + (pack.calculateCountElements() * 100) / (car.getCargoWidthModel() * car.getCargoHeightModel()) <= 50) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
