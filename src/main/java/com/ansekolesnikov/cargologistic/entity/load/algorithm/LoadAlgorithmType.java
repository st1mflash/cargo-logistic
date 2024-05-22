package com.ansekolesnikov.cargologistic.entity.load.algorithm;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.load.LoadPackUtils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class LoadAlgorithmType implements LoadAlgorithm {
    @Override
    public void load(Car car, Pack pack) {
        if (Objects.equals(car.getCargo()[0][0], String.valueOf(pack.getCode()))
                || Objects.equals(car.getCargo()[0][0], "0")) {
            new LoadPackUtils().loadPackInCar(car, pack);
        }
    }
}
