package com.ansekolesnikov.cargologistic.model.pack;

import lombok.Getter;

@Getter
public class Pack {
    private int carId = 0;
    private final int type;
    private final int width;

    public Pack(int type) {
        this.type = type;
        this.width = new PackUtils().calcPackageWidthByType(type);
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

}
