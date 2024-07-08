package com.ansekolesnikov.cargologistic.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Setter
public class Car extends CarModelEntity {
    private String[][] cargo;
    private int idCar;
}
