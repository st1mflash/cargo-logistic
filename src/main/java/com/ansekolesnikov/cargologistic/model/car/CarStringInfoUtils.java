package com.ansekolesnikov.cargologistic.model.car;

public class CarStringInfoUtils {
    public String getCargo(Car car) {
        int[][] cargo = car.getCargo();
        StringBuilder cargoInfo = new StringBuilder();

        for (int i = Car.HEIGHT - 1; i >= 0; i--) {
            cargoInfo.append("+");
            for (int j = 0; j < Car.WIDTH; j++) {
                if (cargo[i][j] == 0) {
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

    public String getFullInfo(Car car) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + car.getId()
                        + "\nПараметры кузова: " + Car.WIDTH + "х" + Car.HEIGHT
                        + "\nЗагруженность: " + new CarUtils().calcPercentLoad(car) + "%"
                        + "\nСостав груза:"
        );

        for (int i = 1; i < 10; i++) {
            int countPackages = new CarUtils().calcCountThisTypePackOnCar(car, i);
            fullInfoString.append((countPackages != 0 ? "\n- тип '" + i + "': " + countPackages + " шт." : ""));
        }
        fullInfoString.append("\nСхема:\n").append(new CarStringInfoUtils().getCargo(car)).append("\n\n");
        return fullInfoString.toString();
    }
}
