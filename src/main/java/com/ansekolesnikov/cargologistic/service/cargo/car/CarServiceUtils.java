package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;

public class CarServiceUtils {
    public CarModel createCarModelFromCommand(CarCommandLine command){
        return new CarModel(
                command.getNameCar(),
                command.getWidthSchemeCargoCar(),
                command.getHeightSchemeCargoCar()
        );
    }
}
