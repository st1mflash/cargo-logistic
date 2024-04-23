package com.ansekolesnikov.cargologistic.model.load_car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;

public class LoadPackageInCar {
    public void loadPackageInCar(Car car, Pack pack) {
        switch (pack.getType()) {
            case 1:
                loadPackageWithType1ToCar(car, pack);
                break;
            case 2:
                loadPackageWithType2ToCar(car, pack);
                break;
            case 3:
                loadPackageWithType3ToCar(car, pack);
                break;
            case 4:
                loadPackageWithType4ToCar(car, pack);
                break;
            case 5:
                loadPackageWithType5ToCar(car, pack);
                break;
            case 6:
                loadPackageWithType6ToCar(car, pack);
                break;
            case 7:
                loadPackageWithType7ToCar(car, pack);
                break;
            case 8:
                loadPackageWithType8ToCar(car, pack);
                break;
            case 9:
                loadPackageWithType9ToCar(car, pack);
                break;
            default:
                break;
        }
    }

    private void loadPackageWithType1ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.WIDTH; i++) {
            for (int j = 0; j < Car.HEIGHT; j++) {
                if (arrCargoScheme[i][j] == 0
                        && new CarUtils().isCanLoadPackOnCargoPosition(car, pack, i, j)
                ) {
                    arrCargoScheme[i][j] = pack.getType();
                    pack.setCarId(car.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType2ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 1; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
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

    private void loadPackageWithType3ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
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

    private void loadPackageWithType4ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
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

    private void loadPackageWithType5ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH - 4; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i][j + 4] == 0
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

    private void loadPackageWithType6ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
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

    private void loadPackageWithType7ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
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

    private void loadPackageWithType8ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 1; i++) {
            for (int j = 0; j < Car.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && arrCargoScheme[i + 1][j + 3] == 0
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

    private void loadPackageWithType9ToCar(Car car, Pack pack) {
        int[][] arrCargoScheme = car.getCargo();
        for (int i = 0; i < Car.HEIGHT - 2; i++) {
            for (int j = 0; j < Car.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && arrCargoScheme[i + 2][j] == 0
                        && arrCargoScheme[i + 2][j + 1] == 0
                        && arrCargoScheme[i + 2][j + 2] == 0
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
