package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
@Getter
public class LoadFileServiceValidation {
    private static final Logger LOGGER = Logger.getLogger(LoadFileServiceValidation.class.getName());
    private String userErrorMessage;

    public boolean isValid(LocalFile localFile, AlgorithmEnum algorithm, int countCars) {
        ViewFileValidation fileValidation = new ViewFileValidation();
        AlgorithmValidation algorithmValidation = new AlgorithmValidation();
        if (!fileValidation.isValid(localFile)) {
            userErrorMessage = fileValidation.getUserErrorMessage();
            return false;
        } else if (!algorithmValidation.isValid(algorithm)) {
            LOGGER.error(algorithmValidation.getLogErrorMessage());
            userErrorMessage = algorithmValidation.getUserErrorMessage();
            return false;
        } else if (countCars <= 0) {
            LOGGER.error(MessageConstant.CAR_COUNT_MUST_BE_MORE_ZERO);
            userErrorMessage = MessageConstant.CAR_COUNT_MUST_BE_MORE_ZERO;
            return false;
        }
        return true;
    }
    public boolean isValidCountCars(List<Car> listCar, int countCars) {
        if(listCar.size() > countCars) {
            LOGGER.error(MessageConstant.CAR_NOT_ENOUGH_ERROR + " Требуется минимум " + listCar.size() + ", а указано " + countCars);
            userErrorMessage = MessageConstant.CAR_NOT_ENOUGH_ERROR;
            return false;
        } else {
            return true;
        }
    }
}
