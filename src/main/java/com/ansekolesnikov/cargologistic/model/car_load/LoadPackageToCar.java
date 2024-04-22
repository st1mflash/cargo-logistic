package com.ansekolesnikov.cargologistic.model.car_load;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;

public class LoadPackageToCar {
    public void loadPackageInCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        switch (cargoPackage.getType()) {
            case 1:
                loadPackageWithType1ToCar(cargoCar, cargoPackage);
                break;
            case 2:
                loadPackageWithType2ToCar(cargoCar, cargoPackage);
                break;
            case 3:
                loadPackageWithType3ToCar(cargoCar, cargoPackage);
                break;
            case 4:
                loadPackageWithType4ToCar(cargoCar, cargoPackage);
                break;
            case 5:
                loadPackageWithType5ToCar(cargoCar, cargoPackage);
                break;
            case 6:
                loadPackageWithType6ToCar(cargoCar, cargoPackage);
                break;
            case 7:
                loadPackageWithType7ToCar(cargoCar, cargoPackage);
                break;
            case 8:
                loadPackageWithType8ToCar(cargoCar, cargoPackage);
                break;
            case 9:
                loadPackageWithType9ToCar(cargoCar, cargoPackage);
                break;
            default:
                break;
        }
    }

    private void loadPackageWithType1ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.WIDTH; i++) {
            for (int j = 0; j < CargoCar.HEIGHT; j++) {
                if (arrCargoScheme[i][j] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType2ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 1; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType3ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType4ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i][j + 3] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType5ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 4; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i][j + 4] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i][j + 3] = cargoPackage.getType();
                    arrCargoScheme[i][j + 4] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());

                    return;
                }
            }
        }
    }

    private void loadPackageWithType6ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 2] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType7ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i][j + 3] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 2] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }

    private void loadPackageWithType8ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i][j + 3] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && arrCargoScheme[i + 1][j + 3] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i][j + 3] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 3] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());

                    return;
                }
            }
        }
    }

    private void loadPackageWithType9ToCar(CargoCar cargoCar, CargoPackage cargoPackage) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        for (int i = 0; i < CargoCar.HEIGHT - 2; i++) {
            for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                if (arrCargoScheme[i][j] == 0
                        && arrCargoScheme[i][j + 1] == 0
                        && arrCargoScheme[i][j + 2] == 0
                        && arrCargoScheme[i + 1][j] == 0
                        && arrCargoScheme[i + 1][j + 1] == 0
                        && arrCargoScheme[i + 1][j + 2] == 0
                        && arrCargoScheme[i + 2][j] == 0
                        && arrCargoScheme[i + 2][j + 1] == 0
                        && arrCargoScheme[i + 2][j + 2] == 0
                        && cargoCar.checkPackageSupport(i, j, cargoPackage.getWidth())
                ) {
                    arrCargoScheme[i][j] = cargoPackage.getType();
                    arrCargoScheme[i][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i + 1][j + 2] = cargoPackage.getType();
                    arrCargoScheme[i + 2][j] = cargoPackage.getType();
                    arrCargoScheme[i + 2][j + 1] = cargoPackage.getType();
                    arrCargoScheme[i + 2][j + 2] = cargoPackage.getType();
                    cargoPackage.setIdCargo(cargoCar.getId());
                    return;
                }
            }
        }
    }
}
