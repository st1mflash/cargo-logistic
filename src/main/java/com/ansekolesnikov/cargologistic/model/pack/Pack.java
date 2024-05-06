package com.ansekolesnikov.cargologistic.model.pack;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pack {
    @Setter
    private int carId = 0;
    private final int type;
    private final int width;

    public Pack(int type) {
        this.type = type;
        this.width = new PackUtils().calcPackageWidthByType(type);
    }
}
