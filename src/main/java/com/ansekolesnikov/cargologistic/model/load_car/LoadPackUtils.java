package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

import java.util.Objects;

public class LoadPackUtils {
    public void loadPackInCar(Car car, Pack pack) {
        if (car.getCargoWidthModel() >= pack.getWidth()) {
            String stringLoadAddress = car.findLoadAddress(pack);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                car.loadPackOnAddress(
                        pack,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                pack.setCarId(car.getIdCar());
            }

        }



/*
        String[][] gameField = car.getCargo();//new int[car.getCargoWidthModel()][car.getCargoHeightModel()]; // игровое поле
        int[][] tetrisShape = pack.toArrayScheme(); // фигура тетриса

        int startX = 0; // начальные координаты по X
        int startY = 0; // начальные координаты по Y

        for (int i = 0; i < tetrisShape.length; i++) {
            for (int j = 0; j < tetrisShape[0].length; j++) {
                if (Objects.equals(gameField[startX + i][startY + j], "0")) { // проверяем, свободна ли клетка на игровом поле
                    gameField[startX + i][startY + j] = String.valueOf(pack.getCode());//String.valueOf(tetrisShape[i][j]); // помещаем фигуру на поле
                } else {
                    // Фигура пересекается с занятой клеткой, попробовать другое место для размещения
                }
            }
        }
        */
        /*
        switch (pack.getCode()) {
            case '1':
                loadPackWithType1ToCar(car, pack);
                break;
            case '2':
                loadPackWithType2ToCar(car, pack);
                break;
            case '3':
                loadPackWithType3ToCar(car, pack);
                break;
            case '4':
                loadPackWithType4ToCar(car, pack);
                break;
            case '5':
                loadPackWithType5ToCar(car, pack);
                break;
            case '6':
                loadPackWithType6ToCar(car, pack);
                break;
            case '7':
                loadPackWithType7ToCar(car, pack);
                break;
            case '8':
                loadPackWithType8ToCar(car, pack);
                break;
            case '9':
                loadPackWithType9ToCar(car, pack);
                break;
            default:
                break;
        }

         */
    }

    private void loadPackWithType1ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoWidthModel(); i++) {
            for (int j = 0; j < car.getCargoHeightModel(); j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType2ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 1; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType3ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 2; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType4ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 3; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 3] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType5ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 4; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && Objects.equals(arrCargoScheme[i][j + 4], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 3] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 4] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());

                    return;
                }
            }
        }
    }

    private void loadPackWithType6ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel() - 1; i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 2; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 2] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType7ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel() - 1; i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 3; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 3] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 2] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }

    private void loadPackWithType8ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel() - 1; i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 3; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 3], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 3] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 3] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());

                    return;
                }
            }
        }
    }

    private void loadPackWithType9ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < car.getCargoHeightModel() - 2; i++) {
            for (int j = 0; j < car.getCargoWidthModel() - 2; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i + 2][j], "0")
                        && Objects.equals(arrCargoScheme[i + 2][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 2][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 1][j + 2] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 2][j] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 2][j + 1] = String.valueOf(pack.getCode());
                    arrCargoScheme[i + 2][j + 2] = String.valueOf(pack.getCode());
                    pack.setCarId(car.getIdCar());
                    return;
                }
            }
        }
    }
}
