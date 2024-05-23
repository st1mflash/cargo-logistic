package com.ansekolesnikov.cargologistic.entity.car.utils;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarModelToStringUtilsTest {
    @Test
    void testToStringCarModelInfo() {
        CarModel carModel = new CarModel("test_car_model", 6, 6);
        String expected = """
                Идентификатор: #null
                Название модели: test_car_model
                Параметры кузова: 6x6""";
        String actual = carModel.toString();

        assertEquals(expected, actual);
    }

}
