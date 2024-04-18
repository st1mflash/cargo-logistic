package com.ansekolesnikov.cargologistic.model;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CargoFile {
    private String name, format, path, content, pathNameFormat;
    private static final Logger LOGGER = Logger.getLogger(CargoFile.class.getName());

    public CargoFile(String filePath) {
        try {
            name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
            format = filePath.substring(filePath.lastIndexOf('.'));
            path = filePath.substring(0, filePath.lastIndexOf('/') + 1);
            pathNameFormat = path + name + format;
            content = Files.readString(Paths.get(path + name + format));
        } catch (Exception e) {
            LOGGER.error("Ошибка считывания содержимого файла '" + filePath + "'");
        }
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }

    public String getFormat() {
        return format;
    }

    public String getName() {
        return name;
    }

    public String getPathNameFormat() {
        return pathNameFormat;
    }
}
