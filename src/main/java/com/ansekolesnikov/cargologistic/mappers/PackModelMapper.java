package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PackModelMapper {
    PackModelDto toDto(PackModelEntity packModelEntity);

    PackModelEntity toEntity(PackModelDto packModelDto);

    @Mapping(target = "arrScheme", expression = "java(getEmptyPackScheme(packModelEntity.getScheme(), packModelEntity.getCode(), packModelEntity.getWidth(), packModelEntity.getHeight()))")
    Pack toPack(PackModelEntity packModelEntity);

    default String[][] getEmptyPackScheme(String schemeString, Character code, int width, int height) {
        String[][] packScheme = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (schemeString.charAt(i * width + j) == '1') {
                    packScheme[i][j] = String.valueOf(code);
                } else {
                    packScheme[i][j] = "0";
                }
            }
        }
        return packScheme;
    }
}
