package com.ansekolesnikov.cargologistic.exception;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class CheckCargoException {
    public static void fileExistException(String fileName) throws IOException {
        String filePath = "src/main/resources/import/cargo/" + fileName;
        if (!Files.exists(Paths.get(filePath))) {
            Logger.getLogger(CheckCargoException.class.getName()).error("Ошибка импорта: файл '" + filePath + "' не найден.");
            throw new NoSuchFileException("Ошибка импорта: файл '" + filePath + "' не найден.");
        }
    }
}
