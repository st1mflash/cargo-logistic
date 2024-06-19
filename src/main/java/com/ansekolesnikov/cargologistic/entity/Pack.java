package com.ansekolesnikov.cargologistic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class Pack extends PackModelEntity {
    private int carId = 0;
    private String[][] arrScheme;
}
