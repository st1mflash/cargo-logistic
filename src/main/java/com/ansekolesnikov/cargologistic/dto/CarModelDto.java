package com.ansekolesnikov.cargologistic.dto;

import com.ansekolesnikov.cargologistic.annotations.Dto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Dto
@NoArgsConstructor
@SuperBuilder
@Data
public class CarModelDto {
    private int id;
    private String name;
    private int width;
    private int height;

    public static CarModelDto to(CarModelEntity carModelEntity) {
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(carModelEntity.getId());
        carModelDto.setName(carModelEntity.getName());
        carModelDto.setWidth(carModelEntity.getWidth());
        carModelDto.setHeight(carModelEntity.getHeight());
        return carModelDto;
    }
}
