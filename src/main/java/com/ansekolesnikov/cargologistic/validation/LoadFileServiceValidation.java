package com.ansekolesnikov.cargologistic.validation;

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
            LOGGER.error("Количество машин должно быть больше нуля");
            userErrorMessage =  "Количество машин должно быть больше нуля";
            return false;
        }
        return true;
    }
    public boolean isValidCountCars(List<Car> listCar, int countCars) {
        if(listCar.size() > countCars) {
            LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + listCar.size() + ", а указано " + countCars);
            userErrorMessage = "Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + listCar.size() + "!";
            return false;
        } else {
            return true;
        }
    }
}
