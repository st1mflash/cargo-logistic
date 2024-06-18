package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.Getter;

//todo Почему вызывается через new? Spring
public class AlgorithmValidation { //todo Почему нет интрефейса
    @Getter//todo почему Get тут а не на сервисе?
    private String userErrorMessage, logErrorMessage; //todo раздели на два
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
