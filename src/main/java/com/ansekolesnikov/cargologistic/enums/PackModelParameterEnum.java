package com.ansekolesnikov.cargologistic.enums;

public enum PackModelParameterEnum {
    NAME,
    CODE,
    SCHEME,
    WIDTH,
    HEIGHT;

    public static PackModelParameterEnum initEnumFromString(String parameter) {
        return switch (parameter.toLowerCase()) {
            case "name" -> NAME;
            case "code" -> CODE;
            case "scheme" -> SCHEME;
            case "scheme_width" -> WIDTH;
            case "scheme_height" -> HEIGHT;
            default -> null;
        };
    }
}
