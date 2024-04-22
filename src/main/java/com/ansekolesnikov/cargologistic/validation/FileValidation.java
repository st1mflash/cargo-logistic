package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.model.file.LocalFile;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileValidation {
    private final String pathFile, nameFile, formatFile;
    private String userErrorMessage, logErrorMessage;

    public FileValidation(LocalFile localFile) {
        this.pathFile = localFile.getPath();
        this.nameFile = localFile.getName();
        this.formatFile = localFile.getFormat();
    }

    public boolean isValid() {
        return isFormatExist()
                && isFileExist();
    }

    private boolean isFileExist() {
        String fileFullPathNameFormat = pathFile + nameFile + formatFile;
        if (!Files.exists(Paths.get(fileFullPathNameFormat))) {
            logErrorMessage = "Ошибка импорта: файл '" + fileFullPathNameFormat + "' не найден.";
            userErrorMessage = "Указанный файл не найден. Убедитесь в корректности указанного формата и наличии файла.";
            return false;
        } else {
            return true;
        }
    }

    private boolean isFormatExist() {
        if (formatFile == null) {
            logErrorMessage = "Ошибка импорта: у файла не указан формат.";
            userErrorMessage = "Не указан формат файла.";
            return false;
        } else {
            return true;
        }
    }

    public String getLogErrorMessage() {
        return logErrorMessage;
    }

    public String getUserErrorMessage() {
        return userErrorMessage;
    }
}
