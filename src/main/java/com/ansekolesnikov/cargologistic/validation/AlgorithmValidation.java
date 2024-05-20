package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.Getter;

public class AlgorithmValidation {
    @Getter
    private String userErrorMessage, logErrorMessage;
    private final AlgorithmEnum algorithm;

    public AlgorithmValidation(AlgorithmEnum algorithm) {
        this.algorithm = algorithm;
    }

    public boolean isValid() {
        return isExist();
    }

    private boolean isExist() {
        if (algorithm == AlgorithmEnum.MAX
                || algorithm == AlgorithmEnum.HALF
                || algorithm == AlgorithmEnum.TYPE
        ) {
            return true;
        } else {
            logErrorMessage = "Ошибка вода: алгоритм '" + algorithm + "' не существует.";
            userErrorMessage = "Введен неверный алгоритм. Доступные значения: 'max', 'half', 'type'.";
            return false;
        }
    }

}
