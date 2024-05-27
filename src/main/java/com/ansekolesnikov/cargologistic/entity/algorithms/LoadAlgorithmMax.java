package com.ansekolesnikov.cargologistic.entity.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.LoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmMax implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        car.loadPack(pack);
    }
}
