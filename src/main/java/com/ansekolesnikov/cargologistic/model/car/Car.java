package com.ansekolesnikov.cargologistic.model.car;

import org.json.JSONObject;

import java.util.Random;

public class Car {
    private int id = new Random().nextInt(1000000);
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final int[][] arrCarScheme = new int[HEIGHT][WIDTH];

    public Car() {
    }

    public Car(JSONObject JSONObj) {
        id = Integer.parseInt(JSONObj.getString("id"));
        setArrCarSchemeFromString(JSONObj.getString("cargo"));
    }

    public String getCarScheme() {
        StringBuilder scheme = new StringBuilder();
        for (int i = HEIGHT - 1; i >= 0; i--) {
            scheme.append("+");
            for (int j = 0; j < WIDTH; j++) {
                if (arrCarScheme[i][j] == 0) {
                    scheme.append(" ");
                } else {
                    scheme.append(arrCarScheme[i][j]);
                }
            }
            scheme.append("+\n");
        }
        scheme.append("++++++++\n");
        return scheme.toString();
    }

    public boolean checkPackageSupport(int coordinateHeight, int coordinateWidth, int packageWidth) {
        if (coordinateHeight == 0) {
            return true;
        } else {
            int sumPackageSupport = 0;
            for (int j = coordinateWidth; j < coordinateWidth + packageWidth; j++) {
                if (arrCarScheme[coordinateHeight - 1][j] != 0) {
                    sumPackageSupport = sumPackageSupport + 1;
                }
            }
            return sumPackageSupport > packageWidth / 2;
        }
    }

    public int getLoadPercent() {
        int fillPoints = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (arrCarScheme[i][j] != 0) {
                    fillPoints++;
                }
            }
        }
        return (fillPoints * 100) / (WIDTH * HEIGHT);
    }

    public int getId() {
        return id;
    }

    public int[][] getArrCarScheme() {
        return arrCarScheme;
    }

    public void setArrCarSchemeFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                arrCarScheme[i][j] = Integer.parseInt(String.valueOf(schemeString.charAt(index++)));
            }
        }
    }
}
