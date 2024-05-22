package com.ansekolesnikov.cargologistic.entity.car.utils;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class CarToStringUtils {
    @Autowired
    private CarUtils carUtils;
    public String toStringCarCargoScheme(Car car) {
        String[][] cargo = car.getCargo();
        StringBuilder cargoInfo = new StringBuilder();

        for (int i = car.getCargoHeightModel() - 1; i >= 0; i--) {
            cargoInfo.append("+");
            for (int j = 0; j < car.getCargoWidthModel(); j++) {
                if (Objects.equals(cargo[i][j], "0")) {
                    cargoInfo.append(" ");
                } else {
                    cargoInfo.append(cargo[i][j]);
                }
            }
            cargoInfo.append("+\n");
        }
        cargoInfo.append("+".repeat(Math.max(0, car.getCargoWidthModel() + 2))).append("\n");
        return cargoInfo.toString();
    }

    public String toStringCarInfo(Car car) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + car.getIdCar()
                        + "\nПараметры кузова: " + car.getCargoWidthModel() + "х" + car.getCargoHeightModel()
                        + "\nЗагруженность: " + carUtils.calcPercentLoad(car) + "%"
                        + "\nСостав кузова:"
        );

        for (int i = 1; i < 10; i++) {
            int countPackages = carUtils.calcCountThisTypePackOnCar(car, i);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + i + "': " + countPackages + " шт." : ""));
        }
        fullInfoString.append("\nСхема кузова:\n").append(new CarToStringUtils().toStringCarCargoScheme(car)).append("\n\n");
        return fullInfoString.toString();
    }
}
