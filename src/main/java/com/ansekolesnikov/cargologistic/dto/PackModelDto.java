package com.ansekolesnikov.cargologistic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class PackModelDto {
    private int id;
    private String name;
    private Character code;
    private String scheme;
    private int width;
    private int height;
}
