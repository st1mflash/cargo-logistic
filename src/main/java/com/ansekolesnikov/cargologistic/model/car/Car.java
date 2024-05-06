package com.ansekolesnikov.cargologistic.model.car;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.model.load_car.algorithm.LoadAlgorithmType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Random;

@Getter
@NoArgsConstructor
public class Car {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    private final int[][] cargo = new int[HEIGHT][WIDTH];
    private int id = new Random().nextInt(1000000);

    public Car(JSONObject JSONObj) {
        id = Integer.parseInt(JSONObj.getString("id"));
        initCargoFromString(JSONObj.getString("cargo"));
    }

    public void loadPack(Pack pack, String algorithm) {
        switch (algorithm) {
            case "max":
                new LoadAlgorithmMax().load(this, pack);
                break;
            case "half":
                new LoadAlgorithmHalf().load(this, pack);
                break;
            case "type":
                new LoadAlgorithmType().load(this, pack);
                break;
            default:
                break;
        }
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
