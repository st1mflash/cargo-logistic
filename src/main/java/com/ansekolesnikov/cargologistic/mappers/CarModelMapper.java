package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CarModelMapper {
    CarModelDto toDto(CarModelEntity carModelEntity);
    CarModelEntity toEntity(CarModelDto carModelDto);
}
