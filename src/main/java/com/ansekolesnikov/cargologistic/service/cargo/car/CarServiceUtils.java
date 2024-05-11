package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

public class CarServiceUtils {
    public Car createPackFromCommand(CarCommandLine command){
        return new Car(
                command.getNameCar(),
                command.getWidthSchemeCargoCar(),
                command.getHeightSchemeCargoCar()
        );
    }
}
