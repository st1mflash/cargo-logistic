package com.ansekolesnikov.cargologistic.validation.service;

import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.validation.AlgorithmValidation;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.apache.log4j.Logger;

public class LoadCarServiceValidation {
    private static final Logger LOGGER = Logger.getLogger(LoadCarServiceValidation.class.getName());
    private final LocalFile localFile;
    private final String algorithm;
    private final int countCars;
    private String userErrorMessage;
    public LoadCarServiceValidation (LocalFile localFile, String algorithm, int countCars) {
        this.localFile = localFile;
        this.algorithm = algorithm;
        this.countCars = countCars;
    }
    public boolean isValid() {
        FileValidation fileValidation = new FileValidation(localFile);
        AlgorithmValidation algorithmValidation = new AlgorithmValidation(algorithm);
        if (!fileValidation.isValid()) {
            LOGGER.error(fileValidation.getLogErrorMessage());
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
    public String getUserErrorMessage() {
        return userErrorMessage;
    }
}
