package com.ansekolesnikov.cargologistic.model.file;

import lombok.Getter;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
public class LocalFile {
    private String name, format, path, content;
    private static final Logger LOGGER = Logger.getLogger(LocalFile.class.getName());

    public LocalFile(String filePath) {
        try {
            name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
            format = filePath.substring(filePath.lastIndexOf('.'));
            path = filePath.substring(0, filePath.lastIndexOf('/') + 1);
            content = Files.readString(Paths.get(path + name + format));
        } catch (Exception e) {
            LOGGER.error("Ошибка считывания содержимого файла '" + filePath + "'. Подробнее: " + e);
        }
    }
}
