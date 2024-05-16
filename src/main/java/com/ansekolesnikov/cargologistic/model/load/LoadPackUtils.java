package com.ansekolesnikov.cargologistic.model.load;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;

import java.util.Objects;

public class LoadPackUtils {
    public void loadPackInCar(Car car, PackModel packModel) {
        if (car.getCargoWidthModel() >= packModel.getWidth()) {
            String stringLoadAddress = car.findLoadPackAddress(packModel);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                car.loadPackOnCargoAddress(
                        packModel,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                packModel.setCarId(car.getIdCar());
            }
        }
    }
}
