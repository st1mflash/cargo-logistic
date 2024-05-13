package com.ansekolesnikov.cargologistic.model.car;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

@Getter
@Setter
public class Car extends CarModel {
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
        this.cargo = new String[cargoHeightModel][cargoWidthModel];
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

    public void loadPackOnCargoAddress(Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")) {
                    cargo[i + height][j + width] = pack.getArrScheme()[i][j];
                }
            }
        }
    }

    public String findLoadPackAddress(Pack pack) {
        for (int i = 0; i < cargoHeightModel; i++) {
            for (int j = 0; j < cargoWidthModel; j++) {
                if (isCanLoadPackOnCargoAddress(pack, i, j)
                        && Objects.equals(cargo[i][j], "0")) {
                    return i + " " + j;
                }
            }
        }
        return "not";
    }

    private boolean isCanLoadPackOnCargoAddress(Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (i + height >= this.cargoHeightModel
                        || j + width >= this.cargoWidthModel) {
                    return false;
                }
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")
                        && !Objects.equals(cargo[i + height][j + width], "0")) {
                    return false;
                }
            }
        }
        return true;
    }
}
