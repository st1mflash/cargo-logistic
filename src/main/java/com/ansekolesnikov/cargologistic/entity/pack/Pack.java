package com.ansekolesnikov.cargologistic.entity.pack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pack extends PackModel {
    private int carId = 0;
    private String[][] arrScheme;

    public Pack(int code) {
        super(code);
        initArrScheme();
    }

    public Pack(PackModel packModel) {
        this.id = packModel.getId();
        this.name = packModel.getName();
        this.code = packModel.getCode();
        this.scheme = packModel.getScheme();
        this.width = packModel.getWidth();
        this.height = packModel.getHeight();
        initArrScheme();
    }

    public void initArrScheme() {
        arrScheme = new String[super.height][width];
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
