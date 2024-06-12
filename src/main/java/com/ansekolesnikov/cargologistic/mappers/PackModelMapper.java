package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PackModelMapper {
    PackModelDto toDto(PackModelEntity packModelEntity);
    PackModelEntity toEntity(PackModelDto packModelDto);
}
