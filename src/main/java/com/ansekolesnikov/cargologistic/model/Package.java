package com.ansekolesnikov.cargologistic.model;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Package {
    private final int id = new Random().nextInt(1000000);
    private int idCargo = 0;
    private final int type;
    private final int[][] arrPackageScheme = new int[5][5];
    private int width;

    public Package(int type) {
        this.type = type;

        initArrayPackageScheme();
        initPackageWidth();
    }

    public static List<Package> sortListDesc(List<Package> listPackages) {
        return listPackages
                .stream()
                .sorted(Comparator.comparingInt(Package::getWidth).reversed())
                .collect(Collectors.toList());
    }

    private void initArrayPackageScheme() {
        switch (type) {
            case 1, 2, 3, 4, 5:
                for (int j = 0; j < type; j++) {
                    arrPackageScheme[0][j] = type;
                }
                break;
            case 6:
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        arrPackageScheme[i][j] = type;
                    }
                }
                break;
            case 7:
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i != 1 && j != 3) {
                            arrPackageScheme[i][j] = type;
                        }
                    }
                }
                break;
            case 8:
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        arrPackageScheme[i][j] = type;
                    }
                }
                break;
            case 9:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        arrPackageScheme[i][j] = type;
                    }
                }
                break;
            default:
                break;
        }
    }

    private void initPackageWidth() {
        switch (type) {
            case 1, 2, 3, 4, 5:
                width = type;
                break;
            case 6, 9:
                width = 3;
                break;
            case 7, 8:
                width = 4;
                break;
            default:
                break;
        }
    }

    /*
    public int getId() {
        return id;
    }

     */
    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getWidth() {
        return width;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public int getType() {
        return type;
    }
    /*
    public int[][] getArrPackageScheme() {
        return arrPackageScheme;
    }

     */
}
