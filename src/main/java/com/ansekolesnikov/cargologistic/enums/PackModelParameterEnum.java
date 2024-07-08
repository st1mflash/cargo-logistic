package com.ansekolesnikov.cargologistic.enums;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PackModelParameterEnum {
    NAME,
    CODE,
    SCHEME,
    WIDTH,
    HEIGHT;
    //todo лучше сделать через значения NAME(BTN_UPDATE_NAME_CAR) или же отдельный маппер
    public static String toStringFromButton(String string) {
        return switch (string) {
            case ButtonConstant.BTN_UPDATE_NAME_PACK -> NAME.toString();
            case ButtonConstant.BTN_UPDATE_CODE_PACK -> CODE.toString();
            case ButtonConstant.BTN_UPDATE_SCHEME_PACK -> SCHEME.toString();
            case ButtonConstant.BTN_UPDATE_WIDTH_PACK -> WIDTH.toString();
            case ButtonConstant.BTN_UPDATE_HEIGHT_PACK -> HEIGHT.toString();
            default -> null;
        };
    }
}
