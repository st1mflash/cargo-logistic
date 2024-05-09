package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;

import java.util.Objects;

public class LoadPackUtils {
    public void loadPackInCar(Car car, Pack pack) {
        switch (pack.getType()) {
            case "1":
                loadPackWithType1ToCar(car, pack);
                break;
            case "2":
                loadPackWithType2ToCar(car, pack);
                break;
            case "3":
                loadPackWithType3ToCar(car, pack);
                break;
            case "4":
                loadPackWithType4ToCar(car, pack);
                break;
            case "5":
                loadPackWithType5ToCar(car, pack);
                break;
            case "6":
                loadPackWithType6ToCar(car, pack);
                break;
            case "7":
                loadPackWithType7ToCar(car, pack);
                break;
            case "8":
                loadPackWithType8ToCar(car, pack);
                break;
            case "9":
                loadPackWithType9ToCar(car, pack);
                break;
            default:
                break;
        }
    }

    private void loadPackWithType1ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.WIDTH; i++) {
            for (int j = 0; j < Car.HEIGHT; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType2ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 1; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType3ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType4ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i][j + 3] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType5ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 4; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && Objects.equals(arrCargoScheme[i][j + 4], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i][j + 3] = pack.getType();
                    arrCargoScheme[i][j + 4] = pack.getType();
                    pack.setCarId(car.getId());

                    return;
                }
            }
        }
    }

    private void loadPackWithType6ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i + 1][j] = pack.getType();
                    arrCargoScheme[i + 1][j + 1] = pack.getType();
                    arrCargoScheme[i + 1][j + 2] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType7ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
                if (Objects.equals(arrCargoScheme[i][j], "0")
                        && Objects.equals(arrCargoScheme[i][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i][j + 2], "0")
                        && Objects.equals(arrCargoScheme[i][j + 3], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 1], "0")
                        && Objects.equals(arrCargoScheme[i + 1][j + 2], "0")
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i][j + 3] = pack.getType();
                    arrCargoScheme[i + 1][j] = pack.getType();
                    arrCargoScheme[i + 1][j + 1] = pack.getType();
                    arrCargoScheme[i + 1][j + 2] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackWithType8ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
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
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i][j + 3] = pack.getType();
                    arrCargoScheme[i + 1][j] = pack.getType();
                    arrCargoScheme[i + 1][j + 1] = pack.getType();
                    arrCargoScheme[i + 1][j + 2] = pack.getType();
                    arrCargoScheme[i + 1][j + 3] = pack.getType();
                    pack.setCarId(car.getId());

                    return;
                }
            }
        }
    }

    private void loadPackWithType9ToCar(Car car, Pack pack) {
        String[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 2; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
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
                    arrCargoScheme[i][j] = pack.getType();
                    arrCargoScheme[i][j + 1] = pack.getType();
                    arrCargoScheme[i][j + 2] = pack.getType();
                    arrCargoScheme[i + 1][j] = pack.getType();
                    arrCargoScheme[i + 1][j + 1] = pack.getType();
                    arrCargoScheme[i + 1][j + 2] = pack.getType();
                    arrCargoScheme[i + 2][j] = pack.getType();
                    arrCargoScheme[i + 2][j + 1] = pack.getType();
                    arrCargoScheme[i + 2][j + 2] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }
}
