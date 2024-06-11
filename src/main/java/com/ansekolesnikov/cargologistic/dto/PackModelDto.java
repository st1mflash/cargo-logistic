package com.ansekolesnikov.cargologistic.dto;

import com.ansekolesnikov.cargologistic.annotations.Dto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
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

    public static PackModelDto to(PackModelEntity packModelEntity) {
        PackModelDto packModelDto = new PackModelDto();
        packModelDto.setId(packModelEntity.getId());
        packModelDto.setName(packModelEntity.getName());
        packModelDto.setCode(packModelEntity.getCode());
        packModelDto.setScheme(packModelEntity.getScheme());
        packModelDto.setWidth(packModelEntity.getWidth());
        packModelDto.setHeight(packModelEntity.getHeight());
        return packModelDto;
    }
}
