package com.ansekolesnikov.cargologistic.entity.utils;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

        StringBuilder cargoString = new StringBuilder();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel(); j++) {
                cargoString.append(car.getCargo()[i][j]);
            }
        }

        for (Character code : Arrays.stream(cargoString.toString().split(""))
                .distinct()
                .map(c -> c.charAt(0))
                .filter(c -> c != '0')
                .toList()
        ) {
            int countPackages = carUtils.calculateCountPackInCarByCode(car, code);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + code + "': " + countPackages + " шт." : ""));
        }

        fullInfoString.append("\nСхема кузова:\n").append(toStringCarCargoScheme(car)).append("\n\n");
        return fullInfoString.toString();
    }
}
