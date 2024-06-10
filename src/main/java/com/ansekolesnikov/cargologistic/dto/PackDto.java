package com.ansekolesnikov.cargologistic.dto;

import lombok.Data;

@Data
public class PackDto {
    private int id;
    private String name;
    private Character code;
    private String scheme;
    private int width;
    private int height;
    private String[][] arrScheme;
}
