package com.ansekolesnikov.cargologistic.model.pack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pack {
    private int carId = 0;
    private int id = 0;
    private String name;
    private int width;
    private int height;
    private String scheme;
    private Character code;
    private String[][] arrScheme;

    public Pack(int code) {
        this.code = Integer.toString(code).charAt(0);
        initArrScheme();
    }

    public Pack(
            int id,
            String name,
            Character code,
            String scheme,
            int width,
            int height
    ) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.scheme = scheme;
        this.code = code;
        initArrScheme();
    }

    public Pack(
            String name,
            int width,
            int height,
            String scheme,
            Character code
    ) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.scheme = scheme;
        this.code = code;
        initArrScheme();
    }

    public int calculateElements() {
        return scheme.replaceAll("0", "").length();
    }

    public void initArrScheme() {
        arrScheme = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (scheme.charAt(i * width + j) == '1') {
                    arrScheme[i][j] = String.valueOf(code);
                } else {
                    arrScheme[i][j] = "0";
                }
            }
        }
    }
}
