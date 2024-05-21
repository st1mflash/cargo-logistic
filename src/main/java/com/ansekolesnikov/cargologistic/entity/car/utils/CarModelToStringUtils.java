package com.ansekolesnikov.cargologistic.entity.car.utils;

import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CarModelToStringUtils {
    public String toStringCarModelInfo(CarModel carModel) {
        return "Идентификатор: #" + carModel.getIdModel()
                + "\nНазвание модели: " + carModel.getNameModel()
                + "\nПараметры кузова: " + carModel.getCargoWidthModel() + "x" + carModel.getCargoHeightModel();
    }
}