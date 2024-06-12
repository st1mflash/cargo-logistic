package com.ansekolesnikov.cargologistic.dto;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.annotations.Dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Dto
@CargoCar
@NoArgsConstructor
@SuperBuilder
@Data
public class CarModelDto {
    private int id;
    private String name;
    private int width;
    private int height;
}
