package com.ansekolesnikov.cargologistic.entity.load;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;

import java.util.Objects;

public class LoadPackUtils {
    public void loadPackInCar(Car car, Pack pack) {
        if (car.getCargoWidthModel() >= pack.getWidth()) {
            String stringLoadAddress = car.findLoadPackAddress(pack);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                car.loadPackOnCargoAddress(
                        pack,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                pack.setCarId(car.getIdCar());
            }
        }
    }
}
