package com.ansekolesnikov.cargologistic.model.car;

import java.util.Arrays;

public class CarInfo {
    public String getCarFullInfo(Car car) {
        StringBuilder fullInfo = new StringBuilder(
                "Идентификатор: #" + car.getId()
                        + "\nПараметры кузова: " + Car.WIDTH + "х" + Car.HEIGHT
                        + "\nЗагруженность: " + car.getLoadPercent() + "%"
                        + "\nСостав груза:"
        );
        for (int i = 1; i < 10; i++) {
            int countPackages = calcPackageCountByType(car, i);
            fullInfo.append((countPackages != 0 ? "\n - тип '" + i + "': " + countPackages + " шт." : ""));
        }
        fullInfo.append("\nСхема кузова:\n").append(getStringCargoScheme(car)).append("\n\n");
        return fullInfo.toString();
    }
    private int calcPackageCountByType(Car car, int cargoPackageType) {
        String loadToString = Arrays.deepToString(car.getArrCarScheme()).replaceAll("\\D", "");
        return (loadToString.length() - (loadToString.replace(Integer.toString(cargoPackageType), "").length())) / cargoPackageType;
    }
    private String getStringCargoScheme(Car car) {
        StringBuilder scheme = new StringBuilder();
        for (int i = Car.HEIGHT - 1; i >= 0; i--) {
            scheme.append("\t\t+");
            for (int j = 0; j < Car.WIDTH; j++) {
                if (car.getArrCarScheme()[i][j] == 0) {
                    scheme.append(" ");
                } else {
                    scheme.append(car.getArrCarScheme()[i][j]);
                }
            }
            scheme.append("+\n");
        }
        scheme.append("\t\t++++++++");
        return scheme.toString();
    }
}
