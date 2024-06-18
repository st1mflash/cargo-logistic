package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CarModelMapper {
    CarModelDto toDto(CarModelEntity carModelEntity);
    CarModelEntity toEntity(CarModelDto carModelDto);
    @Mapping(target = "cargo", expression = "java(getEmptyCargoCarScheme(carModelEntity.getWidth(), carModelEntity.getHeight()))")
    Car toCar(CarModelEntity carModelEntity);

    default String[][] getEmptyCargoCarScheme(int width, int height) {
        String[][] emptyCargoCarScheme = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                emptyCargoCarScheme[i][j] = "0";
            }
        }
        return emptyCargoCarScheme;
    }
}
