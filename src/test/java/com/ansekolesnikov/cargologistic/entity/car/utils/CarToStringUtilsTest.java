package com.ansekolesnikov.cargologistic.entity.car.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarToStringUtilsTest {
    /*
    @Test
    void testToStringCarCargoScheme() {
        CarModel carModel = new CarModel("test_car_model", 6, 6);
        Car car = new Car(carModel);
        car.initCargoFromString("5555514444223330000000000000000000000000");
        CarToStringUtils carToStringUtils = new CarToStringUtils();

        String expected =
                  "+      +" +
                "\n+      +" +
                "\n+      +" +
                "\n+333   +" +
                "\n+444422+" +
                "\n+555551+" +
                "\n++++++++\n"
                ;
        String actual = carToStringUtils.toStringCarCargoScheme(car);
        assertEquals(expected, actual);

        car.initCargoFromString("0000000000000000000000000000000000000000");
        expected =
                          "+      +" +
                        "\n+      +" +
                        "\n+      +" +
                        "\n+      +" +
                        "\n+      +" +
                        "\n+      +" +
                        "\n++++++++\n"
        ;
        actual = carToStringUtils.toStringCarCargoScheme(car);
        assertEquals(expected, actual);

        car.initCargoFromString("555550555550555550555550555550555550");
        expected =
                          "+55555 +" +
                        "\n+55555 +" +
                        "\n+55555 +" +
                        "\n+55555 +" +
                        "\n+55555 +" +
                        "\n+55555 +" +
                        "\n++++++++\n"
        ;
        actual = carToStringUtils.toStringCarCargoScheme(car);
        assertEquals(expected, actual);
    }
    @Test
    void testToStringCarInfo() {
        CarModel carModel = new CarModel("test_car_model", 6, 6);
        Car car = new Car(carModel);
        car.initCargoFromString("5555514444223330000000000000000000000000");
        CarUtils carUtils = new CarUtils();

        String expected = "Идентификатор: #" + car.getIdCar() + "\n" +
                "Параметры кузова: 6х6\n" +
                "Загруженность: 41%\n" +
                "Состав кузова:\n" +
                "- посылка '1': 1 шт.\n" +
                "- посылка '2': 1 шт.\n" +
                "- посылка '3': 1 шт.\n" +
                "- посылка '4': 1 шт.\n" +
                "- посылка '5': 1 шт.\n" +
                "Схема кузова:\n" +
                "+      +\n" +
                "+      +\n" +
                "+      +\n" +
                "+333   +\n" +
                "+444422+\n" +
                "+555551+\n" +
                "++++++++\n" +
                "\n\n";

        String actual = carUtils.toStringCarInfo(car);

        assertEquals(expected, actual);
    }
*/
}
