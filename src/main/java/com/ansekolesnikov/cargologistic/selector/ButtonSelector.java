package com.ansekolesnikov.cargologistic.selector;

import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ansekolesnikov.cargologistic.constants.ButtonConstant.*;

@NoArgsConstructor
@Component
public class ButtonSelector {
    public String toCommandParameter(String buttonName) {
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

    public StateEnum toNextStateEnum(String buttonName) {
        return switch (buttonName) {
            case BTN_PACK_LIST -> StateEnum.GET_LIST_PACK;
            case BTN_CAR_LIST -> StateEnum.GET_LIST_CAR;
            case BTN_GET_PACK -> StateEnum.INPUT_ID_FOR_GET_PACK;
            case BTN_GET_CAR -> StateEnum.INPUT_ID_FOR_GET_CAR;
            case BTN_INSERT_PACK -> StateEnum.INPUT_NAME_FOR_INSERT_PACK;
            case BTN_INSERT_CAR -> StateEnum.INPUT_NAME_FOR_INSERT_CAR;
            case BTN_DELETE_PACK -> StateEnum.INPUT_ID_FOR_DELETE_PACK;
            case BTN_DELETE_CAR -> StateEnum.INPUT_ID_FOR_DELETE_CAR;
            case BTN_UPDATE_PACK -> StateEnum.INPUT_ID_FOR_UPDATE_PACK;
            case BTN_UPDATE_CAR -> StateEnum.INPUT_ID_FOR_UPDATE_CAR;
            case BTN_LOAD_FILE -> StateEnum.INPUT_FILENAME_FOR_LOAD_FILE;
            case BTN_LOAD_LIST -> StateEnum.INPUT_NAME_CAR_FOR_LOAD_LIST;
            case BTN_VIEW_FILE -> StateEnum.INPUT_FILENAME_FOR_VIEW_FILE;
            default -> null;
        };
    }
}
