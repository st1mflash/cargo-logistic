package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import org.json.JSONObject;
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
    @Mapping(target = "idCar", expression = "java(Integer.parseInt(jsonObj.getString(\"id\")))")
    @Mapping(target = "width", expression = "java(6)") //todo есть constant
    @Mapping(target = "height", expression = "java(6)") //todo есть constant
    @Mapping(target = "cargo", expression = "java(getCarCargoSchemeByString(jsonObj.getString(\"cargo\"), 6, 6))")
    Car toCar(JSONObject jsonObj);

    default String[][] getEmptyCargoCarScheme(int width, int height) {
        String[][] emptyCargoCarScheme = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                emptyCargoCarScheme[i][j] = "0";
            }
        }
        return emptyCargoCarScheme;
    }

    default String[][] getCarCargoSchemeByString(String schemeString, int width, int height) {
        String[][] carCargoScheme = new String[height][width];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                carCargoScheme[i][j] = String.valueOf(schemeString.charAt(index++));
            }
        }
        return carCargoScheme;
    }
}
