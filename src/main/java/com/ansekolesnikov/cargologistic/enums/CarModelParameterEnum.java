package com.ansekolesnikov.cargologistic.enums;

public enum CarModelParameterEnum {
    NAME,
    WIDTH,
    HEIGHT;

    public static CarModelParameterEnum initEnumFromString(String parameter) {
        return switch (parameter.toLowerCase()) {
            case "name" -> NAME;
            case "cargo_width" -> WIDTH;
            case "cargo_height" -> HEIGHT;
            default -> null;
        };
    }
}
