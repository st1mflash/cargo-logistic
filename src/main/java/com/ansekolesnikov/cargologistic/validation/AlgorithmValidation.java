package com.ansekolesnikov.cargologistic.validation;

public class AlgorithmValidation {
    private final String algorithm;
    private String userErrorMessage, logErrorMessage;

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

    public String getUserErrorMessage() {
        return userErrorMessage;
    }

    public String getLogErrorMessage() {
        return logErrorMessage;
    }
}
