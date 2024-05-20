package com.ansekolesnikov.cargologistic.enums;

public enum AlgorithmEnum {
    MAX,
    HALF,
    TYPE;

    public static AlgorithmEnum initEnumFromString(String algorithm) {
        return switch (algorithm.toLowerCase()) {
            case "max" -> MAX;
            case "half" -> HALF;
            case "type" -> TYPE;
            default -> null;
        };
    }
}
