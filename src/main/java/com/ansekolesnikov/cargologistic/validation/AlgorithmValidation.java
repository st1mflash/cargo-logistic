package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
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
        if (EnumUtils.isValidEnum(AlgorithmEnum.class, algorithm.name())) {
            return true;
        } else {
            logErrorMessage = MessageConstant.UNKNOWN_ALGORITHM + " " + algorithm;
            userErrorMessage = MessageConstant.UNKNOWN_ALGORITHM;
            return false;
        }
    }
}
