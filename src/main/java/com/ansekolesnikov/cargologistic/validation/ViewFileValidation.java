package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.MessageConstant;
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
            LOGGER.error(MessageConstant.FILE_NOT_FOUND_ERROR + " " + fileFullPathNameFormat);
            userErrorMessage = MessageConstant.FILE_NOT_FOUND_ERROR;
            return false;
        } else {
            return true;
        }
    }

    private boolean isFormatExist(String formatFile) {
        if (formatFile == null) {
            LOGGER.error(MessageConstant.FILE_FORMAT_NOT_FOUND_ERROR);
            userErrorMessage = MessageConstant.FILE_FORMAT_NOT_FOUND_ERROR;
            return false;
        } else {
            return true;
        }
    }

}
