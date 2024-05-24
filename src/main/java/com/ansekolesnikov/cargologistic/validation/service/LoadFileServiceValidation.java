package com.ansekolesnikov.cargologistic.validation.service;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.validation.AlgorithmValidation;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.List;

public class LoadFileServiceValidation {
    private static final Logger LOGGER = Logger.getLogger(LoadFileServiceValidation.class.getName());
    private final LocalFile localFile;
    private final AlgorithmEnum algorithm;
    private final int countCars;
    @Getter
    private String userErrorMessage;
    public LoadFileServiceValidation(LocalFile localFile, AlgorithmEnum algorithm, int countCars) {
        this.localFile = localFile;
        this.algorithm = algorithm;
        this.countCars = countCars;
    }
    public boolean isValid() {
        FileValidation fileValidation = new FileValidation(localFile);
        AlgorithmValidation algorithmValidation = new AlgorithmValidation(algorithm);
        if (!fileValidation.isValid()) {
            userErrorMessage = fileValidation.getUserErrorMessage();
            return false;
        } else if (!algorithmValidation.isValid()) {
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
    public boolean isValidCountCars(List<Car> listCar) {
        if(listCar.size() > countCars) {
            LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + listCar.size() + ", а указано " + countCars);
            userErrorMessage = "Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + listCar.size() + "!";
            return false;
        } else {
            return true;
        }
    }
}
