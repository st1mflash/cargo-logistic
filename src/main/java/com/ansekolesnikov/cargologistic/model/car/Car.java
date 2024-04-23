package com.ansekolesnikov.cargologistic.model.car;

import org.json.JSONObject;

import java.util.Random;

public class Car {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final int[][] cargo = new int[HEIGHT][WIDTH];
    private int id = new Random().nextInt(1000000);

    public Car() {}
    public Car(JSONObject JSONObj) {
        id = Integer.parseInt(JSONObj.getString("id"));
        initCargoFromString(JSONObj.getString("cargo"));
    }
    public int getId() {
        return id;
    }
    public int[][] getCargo() {
        return cargo;
    }
    public void initCargoFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                cargo[i][j] = Integer.parseInt(String.valueOf(schemeString.charAt(index++)));
            }
        }
    }
}
