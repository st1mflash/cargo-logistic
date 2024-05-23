package com.ansekolesnikov.cargologistic.enums;

public enum DatabaseOperationEnum {
    LIST,
    GET,
    INSERT,
    UPDATE,
    DELETE;

    public static DatabaseOperationEnum initEnumFromString(String operation) {
        return switch (operation.toLowerCase()) {
            case "list" -> LIST;
            case "get" -> GET;
            case "insert" -> INSERT;
            case "update" -> UPDATE;
            case "delete" -> DELETE;
            default -> null;
        };
    }

}
