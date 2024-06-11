package com.ansekolesnikov.cargologistic.algorithms;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILoadAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class LoadAlgorithmType implements ILoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (Objects.equals(car.getCargo()[0][0], String.valueOf(pack.getCode()))
                || Objects.equals(car.getCargo()[0][0], "0")) {
            car.loadPack(pack);
        }
    }
}
