package com.ansekolesnikov.cargologistic.entity.algorithms;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.utils.LoadPackUtils;
import com.ansekolesnikov.cargologistic.interfaces.LoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmMax implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        new LoadPackUtils().loadPackInCar(car, pack);
    }
}
