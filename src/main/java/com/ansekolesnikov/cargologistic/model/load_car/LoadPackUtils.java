package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

import java.util.Objects;

public class LoadPackUtils {
    public void loadPackInCar(Car car, Pack pack) {
        if (car.getCargoWidthModel() >= pack.getWidth()) {
            String stringLoadAddress = car.findLoadAddress(pack);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                car.loadPackOnAddress(
                        pack,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                pack.setCarId(car.getIdCar());
            }
        }
    }
}
