package com.ansekolesnikov.cargologistic.mappers;

import static com.ansekolesnikov.cargologistic.constants.ButtonConstant.*;

import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ButtonMapper {
    default String toParameterName(String buttonName) {
        return switch (buttonName) {
            case BTN_UPDATE_NAME_CAR -> CarModelParameterEnum.NAME.name().toLowerCase();
            case BTN_UPDATE_WIDTH_CAR -> CarModelParameterEnum.WIDTH.name().toLowerCase();
            case BTN_UPDATE_HEIGHT_CAR -> CarModelParameterEnum.HEIGHT.name().toLowerCase();
            case BTN_UPDATE_NAME_PACK -> PackModelParameterEnum.NAME.name().toLowerCase();
            case BTN_UPDATE_CODE_PACK -> PackModelParameterEnum.CODE.name().toLowerCase();
            case BTN_UPDATE_SCHEME_PACK -> PackModelParameterEnum.SCHEME.name().toLowerCase();
            case BTN_UPDATE_WIDTH_PACK -> PackModelParameterEnum.WIDTH.name().toLowerCase();
            case BTN_UPDATE_HEIGHT_PACK -> PackModelParameterEnum.HEIGHT.name().toLowerCase();
            default -> null;
        };
    }
}
