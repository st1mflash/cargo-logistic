package com.ansekolesnikov.cargologistic.model;

import org.json.JSONObject;
import java.util.Arrays;
import java.util.Random;
public class CargoCar {
    private int id = new Random().nextInt(1000000);
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final int[][] arrCargoScheme = new int[HEIGHT][WIDTH];

    public CargoCar() {
    }

    public CargoCar(JSONObject JSONObj) {
        id = Integer.parseInt(JSONObj.getString("id"));
        initCargoSchemeFromString(JSONObj.getString("cargo"));
    }

    public String getCargoCarFullInfo() {
        StringBuilder fullInfo = new StringBuilder(
                "Идентификатор: #" + id
                        + "\nПараметры кузова: " + CargoCar.WIDTH + "х" + CargoCar.HEIGHT
                        + "\nЗагруженность: " + getLoadPercent() + "%"
                        + "\nСостав груза:"
        );
        for (int i = 1; i < 10; i++) {
            fullInfo.append((countTypeLoad(i) != 0 ? "\n - тип '" + i + "': " + countTypeLoad(i) + " шт." : ""));
        }
        fullInfo.append("\nСхема кузова:\n").append(getStringCargoScheme()).append("\n\n");
        return fullInfo.toString();
    }

    private int countTypeLoad(int type) {
        String loadToString = Arrays.deepToString(arrCargoScheme).replaceAll("\\D", "");
        return (loadToString.length() - (loadToString.replace(Integer.toString(type), "").length())) / type;
    }

    public String getCargoScheme() {
        StringBuilder scheme = new StringBuilder();
        for (int i = HEIGHT - 1; i >= 0; i--) {
            scheme.append("+");
            for (int j = 0; j < WIDTH; j++) {
                if (arrCargoScheme[i][j] == 0) {
                    scheme.append(" ");
                } else {
                    scheme.append(arrCargoScheme[i][j]);
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
                if (arrCargoScheme[coordinateHeight - 1][j] != 0) {
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
                if (arrCargoScheme[i][j] != 0) {
                    fillPoints++;
                }
            }
        }
        return (fillPoints * 100) / (WIDTH * HEIGHT);
    }

    public int getId() {
        return id;
    }

    public int[][] getArrCargoScheme() {
        return arrCargoScheme;
    }

    public void initCargoSchemeFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH; j++) {
                arrCargoScheme[i][j] = Integer.parseInt(String.valueOf(schemeString.charAt(index++)));
            }
        }
    }

    public String getStringCargoScheme() {
        StringBuilder scheme = new StringBuilder();
        for (int i = HEIGHT - 1; i >= 0; i--) {
            scheme.append("\t\t+");
            for (int j = 0; j < WIDTH; j++) {
                if (arrCargoScheme[i][j] == 0) {
                    scheme.append(" ");
                } else {
                    scheme.append(arrCargoScheme[i][j]);
                }
            }
            scheme.append("+\n");
        }
        scheme.append("\t\t++++++++");
        return scheme.toString();
    }
}
