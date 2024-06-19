package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class AlgorithmValidation {
    private String userErrorMessage;
    private String logErrorMessage;

    public boolean isValid(AlgorithmEnum algorithm) {
        return isExist(algorithm);
    }

    private boolean isExist(AlgorithmEnum algorithm) {
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
