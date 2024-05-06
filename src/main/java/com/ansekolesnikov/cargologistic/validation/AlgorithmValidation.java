package com.ansekolesnikov.cargologistic.validation;

import lombok.Getter;

public class AlgorithmValidation {
    @Getter
    private String userErrorMessage, logErrorMessage;
    private final String algorithm;

    public AlgorithmValidation(String algorithm) {
        this.algorithm = algorithm.toLowerCase();
    }

    public boolean isValid() {
        return isExist();
    }

    private boolean isExist() {
        if (algorithm.equals("max")
                || algorithm.equals("half")
                || algorithm.equals("type")
        ) {
            return true;
        } else {
            logErrorMessage = "Ошибка вода: алгоритм '" + algorithm + "' не существует.";
            userErrorMessage = "Введен неверный алгоритм. Доступные значения: 'max', 'half', 'type'.";
            return false;
        }
    }

}
