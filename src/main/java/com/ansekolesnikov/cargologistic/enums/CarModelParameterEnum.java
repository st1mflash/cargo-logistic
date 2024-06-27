package com.ansekolesnikov.cargologistic.enums;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;

public enum CarModelParameterEnum {
    NAME,
    WIDTH,
    HEIGHT;

    public static String toStringFromButton(String string) {
        return switch (string) {
            case ButtonConstant.BTN_UPDATE_NAME_CAR -> NAME.toString();
            case ButtonConstant.BTN_UPDATE_WIDTH_CAR -> WIDTH.toString();
            case ButtonConstant.BTN_UPDATE_HEIGHT_CAR -> HEIGHT.toString();
            default -> null;
        };
    }
}
