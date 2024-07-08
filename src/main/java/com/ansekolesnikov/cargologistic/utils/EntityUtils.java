package com.ansekolesnikov.cargologistic.utils;

import com.ansekolesnikov.cargologistic.enums.*;

public class EntityUtils { //todo это не utils. Больше похоже на Enumы. Надо сделать их отдельными классами ,
    // а так же сделать не через case а через енамы с значениями
    public static AlgorithmEnum getAlgorithmEnum(String algorithm) {
        return switch (algorithm.toLowerCase()) {
            case "max" -> AlgorithmEnum.MAX;
            case "half" -> AlgorithmEnum.HALF;
            case "type" -> AlgorithmEnum.TYPE;
            default -> null;
        };
    }

    public static CarModelParameterEnum getCarModelParameterEnum(String parameter) {
        return switch (parameter.toLowerCase()) {
            case "name" -> CarModelParameterEnum.NAME;
            case "width" -> CarModelParameterEnum.WIDTH;
            case "height" -> CarModelParameterEnum.HEIGHT;
            default -> null;
        };
    }

    public static DatabaseOperationEnum getDatabaseOperationEnum(String operation) {
        return switch (operation.toLowerCase()) {
            case "list" -> DatabaseOperationEnum.LIST;
            case "get" -> DatabaseOperationEnum.GET;
            case "insert" -> DatabaseOperationEnum.INSERT;
            case "update" -> DatabaseOperationEnum.UPDATE;
            case "delete" -> DatabaseOperationEnum.DELETE;
            default -> null;
        };
    }

    public static PackModelParameterEnum getPackModelParameterEnum(String parameter) {
        return switch (parameter.toLowerCase()) {
            case "name" -> PackModelParameterEnum.NAME;
            case "code" -> PackModelParameterEnum.CODE;
            case "scheme" -> PackModelParameterEnum.SCHEME;
            case "width" -> PackModelParameterEnum.WIDTH;
            case "height" -> PackModelParameterEnum.HEIGHT;
            default -> null;
        };
    }
}
