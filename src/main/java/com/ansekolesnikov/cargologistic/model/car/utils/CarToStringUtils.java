package com.ansekolesnikov.cargologistic.model.car.utils;

import com.ansekolesnikov.cargologistic.model.car.Car;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class CarToStringUtils {
    public String toStringCarCargoScheme(Car car) {
        String[][] cargo = car.getCargo();
        StringBuilder cargoInfo = new StringBuilder();

        for (int i = Car.HEIGHT - 1; i >= 0; i--) {
            cargoInfo.append("+");
            for (int j = 0; j < Car.WIDTH; j++) {
                if (Objects.equals(cargo[i][j], "0")) {
                    cargoInfo.append(" ");
                } else {
                    cargoInfo.append(cargo[i][j]);
                }
            }
            cargoInfo.append("+\n");
        }
        cargoInfo.append("++++++++\n");
        return cargoInfo.toString();
    }

    public String toStringCarInfo(Car car) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + car.getIdCar()
                        + "\nПараметры кузова: " + car.getCargoWidthModel() + "х" + car.getCargoHeightModel()
                        + "\nЗагруженность: " + new CarUtils().calcPercentLoad(car) + "%"
                        + "\nСостав груза:"
        );

        for (int i = 1; i < 10; i++) {
            int countPackages = new CarUtils().calcCountThisTypePackOnCar(car, i);
            fullInfoString.append((countPackages != 0 ? "\n- тип '" + i + "': " + countPackages + " шт." : ""));
        }
        fullInfoString.append("\nСхема:\n").append(new CarToStringUtils().toStringCarCargoScheme(car)).append("\n\n");
        return fullInfoString.toString();
    }
}
