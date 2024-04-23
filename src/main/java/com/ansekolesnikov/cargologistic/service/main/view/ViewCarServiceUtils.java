package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfo;

import java.util.List;

public class ViewCarServiceUtils {
    public String getListCarsResultString(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(new CarStringInfo().getFullInfo(car));
        }
        return result.toString();
    }
}
