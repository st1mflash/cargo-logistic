package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.LoadFileService;
import com.ansekolesnikov.cargologistic.service.ViewFileService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
public class ShellConsoleController {
    private final ViewFileService viewFileCargoService;
    private final LoadFileService loadFileCargoService;

    private static final Logger LOGGER = Logger.getLogger(ShellConsoleController.class.getName());

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load_file(
            @ShellOption String fileName,
            @ShellOption String algorithm,
            @ShellOption String countCars
    ) {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return loadFileCargoService.run(
                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                        fileName + " " +
                        algorithm + " " +
                        countCars
        );
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view_file(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return viewFileCargoService.run(
                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                        fileName
        );
    }
}
