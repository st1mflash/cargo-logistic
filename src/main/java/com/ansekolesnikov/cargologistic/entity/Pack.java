package com.ansekolesnikov.cargologistic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class Pack extends PackModelEntity {
    private int carId = 0;
    private String[][] arrScheme;

    public Pack(PackModelEntity packModelEntity) {
        this.id = packModelEntity.getId();
        this.name = packModelEntity.getName();
        this.code = packModelEntity.getCode();
        this.scheme = packModelEntity.getScheme();
        this.width = packModelEntity.getWidth();
        this.height = packModelEntity.getHeight();
        initArrScheme();
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
