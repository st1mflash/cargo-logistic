package com.ansekolesnikov.cargologistic.model.car;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Random;

@Getter
@Setter
public class Car {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final String[][] cargo = new String[HEIGHT][WIDTH];
    private int id;
    private String name;
    private String cargoScheme;
    private int width;
    private int height;

    public Car() {
        id = new Random().nextInt(1000000);
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    public Car(
            String name,
            int width,
            int height
    ) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Car(
            int id,
            String name,
            int width,
            int height
    ) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
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
