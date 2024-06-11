package com.ansekolesnikov.cargologistic.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class LoadAlgorithmMax implements ILoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        car.loadPack(pack);
    }
}
