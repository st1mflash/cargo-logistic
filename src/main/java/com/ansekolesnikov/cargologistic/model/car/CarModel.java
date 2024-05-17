package com.ansekolesnikov.cargologistic.model.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer idModel;
    @Column(name = "name")
    protected String nameModel;
    @Column(name = "cargo_width")
    protected int cargoWidthModel;
    @Column(name = "cargo_height")
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
