package com.ansekolesnikov.cargologistic.model.car;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Random;

@Getter
@Setter
public class Car extends CarModel {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final String[][] cargo = new String[HEIGHT][WIDTH];
    private int idCar;
    //private int idModel;
    //private String name;
    //private String cargoScheme;
    //private int cargoWidth;
    //private int cargoHeight;

    public Car() {
        //super();
        /*
        idModel = 0;
        nameModel = "";
        cargoWidthModel = 0;
        cargoHeightModel = 0;
        */

        idCar = new Random().nextInt(1000000);
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    /*
    public Car(
            String name,
            int cargoWidth,
            int cargoHeight
    ) {
        this.nameModel = name;
        this.cargoWidthModel = cargoWidth;
        this.cargoHeightModel = cargoHeight;
    }
    */

    /*
    public Car(
            int idModel,
            String name,
            int cargoWidth,
            int cargoHeight
    ) {
        this.idModel = idModel;
        this.nameModel = name;
        this.cargoWidthModel = cargoWidth;
        this.cargoHeightModel = cargoHeight;
    }
    */

    public Car(JSONObject JSONObj) {
        idCar = Integer.parseInt(JSONObj.getString("id"));
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
