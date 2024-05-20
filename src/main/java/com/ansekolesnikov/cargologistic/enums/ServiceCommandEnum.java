package com.ansekolesnikov.cargologistic.enums;

public enum ServiceCommandEnum {
    INFO,
    LOAD_FILE,
    LOAD_LIST,
    VIEW_FILE,
    CAR,
    PACK;

    public static ServiceCommandEnum initEnumFromString(String command) {
        return switch (command.toLowerCase()) {
            case "/info" -> INFO;
            case "load_file" -> LOAD_FILE;
            case "load_list" -> LOAD_LIST;
            case "view_file" -> VIEW_FILE;
            case "car" -> CAR;
            case "pack" -> PACK;
            default -> null;
        };
    }
}
