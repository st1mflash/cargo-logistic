package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.entity.LocalFile;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileValidation {
    private static final Logger LOGGER = Logger.getLogger(FileValidation.class.getName());
    private final String pathFile, nameFile, formatFile;
    @Getter
    private String userErrorMessage;

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
            LOGGER.error("Ошибка импорта: файл '" + fileFullPathNameFormat + "' не найден.");
            userErrorMessage = "Указанный файл не найден. Убедитесь в корректности указанного формата и наличии файла.";
            return false;
        } else {
            return true;
        }
    }

    private boolean isFormatExist() {
        if (formatFile == null) {
            LOGGER.error("Ошибка импорта: у файла не указан формат.");
            userErrorMessage = "Не указан формат файла.";
            return false;
        } else {
            return true;
        }
    }

}
