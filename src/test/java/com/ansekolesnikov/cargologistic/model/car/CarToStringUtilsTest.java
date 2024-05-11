package com.ansekolesnikov.cargologistic.model.car;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarToStringUtilsTest {
    @Test
    void testGetCargo() {
        Car car = new Car();
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
    void testGetFullInfo() {
        Car car = new Car();
        car.initCargoFromString("5555514444223330000000000000000000000000");
        CarToStringUtils carToStringUtils = new CarToStringUtils();

        String expected = "Идентификатор: #" + car.getId() + "\n" +
                "Параметры кузова: 6х6\n" +
                "Загруженность: 41%\n" +
                "Состав груза:\n" +
                "- тип '1': 1 шт.\n" +
                "- тип '2': 1 шт.\n" +
                "- тип '3': 1 шт.\n" +
                "- тип '4': 1 шт.\n" +
                "- тип '5': 1 шт.\n" +
                "Схема:\n" +
                "+      +\n" +
                "+      +\n" +
                "+      +\n" +
                "+333   +\n" +
                "+444422+\n" +
                "+555551+\n" +
                "++++++++\n" +
                "\n\n";

        String actual = carToStringUtils.toStringCarInfo(car);
        System.out.println(expected);
        System.out.println(actual);

        assertEquals(expected, actual);
    }
}
