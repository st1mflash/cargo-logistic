package com.ansekolesnikov.cargologistic.dto;

import lombok.Data;

@Data
public class CarDto {
    private int id;
    private String name;
    private int width;
    private int height;
    private String[][] cargo;
}
