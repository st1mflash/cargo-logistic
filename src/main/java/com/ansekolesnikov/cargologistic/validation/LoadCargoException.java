package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.service.ViewService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class LoadCargoException {
    private static final Logger LOGGER = Logger.getLogger(ViewService.class.getName());
    public static void algorithmExistException(String algorithm) throws Exception {
        algorithm = algorithm.toLowerCase();
        if (
                !algorithm.equals("max")
                        && !algorithm.equals("half")
                        && !algorithm.equals("type")
        ) {
            LOGGER.error("Ошибка ввода: не удалось определить алгоритм загрузки '" + algorithm + "'");
            throw new Exception("Ошибка ввода: не удалось определить алгоритм загрузки '" + algorithm + "'");
        }
    }

    public static void fileExistException(String fileName) throws IOException {
        String filePath = "src/main/resources/import/packages/" + fileName;
        if (!Files.exists(Paths.get(filePath))) {
            LOGGER.error("Ошибка импорта: файл '" + filePath + "' не найден.");
            throw new NoSuchFileException("Ошибка импорта: файл '" + filePath + "' не найден.");
        }
    }
}
