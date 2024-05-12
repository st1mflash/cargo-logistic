package com.ansekolesnikov.cargologistic.model.car;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Random;

@Getter
@Setter
public class Car extends CarModel {
    //public static final int WIDTH = 6;
    //public static final int HEIGHT = 6;
    private String[][] cargo;
    private int idCar = new Random().nextInt(1000000);

    public Car() {
        this.cargoWidthModel = 6;
        this.cargoHeightModel = 6;
        cargo = new String[cargoHeightModel][cargoWidthModel];
        for (int i = 0; i < cargoHeightModel; i++) {
            for (int j = 0; j < cargoWidthModel; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    public Car(CarModel carModel) {
        this.idModel = carModel.getIdModel();
        this.nameModel = carModel.getNameModel();
        this.cargoWidthModel = carModel.getCargoWidthModel();
        this.cargoHeightModel = carModel.getCargoHeightModel();
        this.cargo = new String[cargoHeightModel][cargoWidthModel];
        for (int i = 0; i < cargoHeightModel; i++) {
            for (int j = 0; j < cargoWidthModel; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    public Car(JSONObject JSONObj) {
        this.idCar = Integer.parseInt(JSONObj.getString("id"));
        this.cargoWidthModel = 6;
        this.cargoHeightModel = 6;
        initCargoFromString(JSONObj.getString("cargo"));
    }

    public void initCargoFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < cargoHeightModel; i++) {
            for (int j = 0; j < cargoWidthModel; j++) {
                cargo[i][j] = String.valueOf(schemeString.charAt(index++));
            }
        }
    }
}
