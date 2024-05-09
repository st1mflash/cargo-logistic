package com.ansekolesnikov.cargologistic.model.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

@Getter
public class Car {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final String[][] cargo = new String[HEIGHT][WIDTH];
    private int id = new Random().nextInt(1000000);

    public Car() {
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    public Car(JSONObject JSONObj) {
        id = Integer.parseInt(JSONObj.getString("id"));
        initCargoFromString(JSONObj.getString("cargo"));
    }

    public void initCargoFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                cargo[i][j] = String.valueOf(schemeString.charAt(index++));
            }
        }
    }
}
