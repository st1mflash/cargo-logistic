package com.ansekolesnikov.cargologistic.validation;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewServiceValidation {
    private String message;
    private static final Logger LOGGER = Logger.getLogger(ViewServiceValidation.class.getName());

    public boolean isFileExist(String filePath) {
        if (!Files.exists(Paths.get(filePath))) {
            String errorMessage = "Ошибка импорта: файл '" + filePath + "' не найден.";
            LOGGER.error(errorMessage);
            message = errorMessage;
            return false;
        }
        return true;
    }

    public String getMessage() {
        return message;
    }
}
