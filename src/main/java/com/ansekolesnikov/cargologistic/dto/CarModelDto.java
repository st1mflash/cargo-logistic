package com.ansekolesnikov.cargologistic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class CarModelDto {
    private int id;
    private String name;
    private int width;
    private int height;
}
