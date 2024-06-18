package com.ansekolesnikov.cargologistic.dto;

import com.ansekolesnikov.cargologistic.annotations.Dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Dto
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
