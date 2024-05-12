package com.ansekolesnikov.cargologistic.model.car;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarModel {
    protected int idModel;
    protected String nameModel;
    protected int cargoWidthModel;
    protected int cargoHeightModel;

    public CarModel(
            String name,
            int cargoWidth,
            int cargoHeight
    ) {
        this.nameModel = name;
        this.cargoWidthModel = cargoWidth;
        this.cargoHeightModel = cargoHeight;
    }

    public CarModel(
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

}
