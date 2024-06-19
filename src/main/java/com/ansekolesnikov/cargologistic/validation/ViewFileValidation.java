package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.entity.LocalFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
@NoArgsConstructor
@Component
public class ViewFileValidation {
    private static final Logger LOGGER = Logger.getLogger(ViewFileValidation.class.getName());
    private String userErrorMessage;

    public boolean isValid(LocalFile localFile) {
        return isFormatExist(localFile.getFormat()) && isFileExist(localFile.getPath(), localFile.getName(), localFile.getFormat());
    }

    private boolean isFileExist(String pathFile, String nameFile, String formatFile) {
        String fileFullPathNameFormat = pathFile + nameFile + formatFile;
        if (!Files.exists(Paths.get(fileFullPathNameFormat))) {
            LOGGER.error("Ошибка импорта: файл '" + fileFullPathNameFormat + "' не найден.");
            userErrorMessage = "Указанный файл не найден. Убедитесь в корректности указанного формата и наличии файла.";
            return false;
        } else {
            return true;
        }
    }

    private boolean isFormatExist(String formatFile) {
        if (formatFile == null) {
            LOGGER.error("Ошибка импорта: у файла не указан формат.");
            userErrorMessage = "Не указан формат файла.";
            return false;
        } else {
            return true;
        }
    }

}
