package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.LoadFileService;
import com.ansekolesnikov.cargologistic.service.ViewFileService;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellConsoleController {
    private final ViewFileService viewFileCargoService;
    private final LoadFileService loadFileCargoService;

    public ShellConsoleController(ViewFileService viewFileCargoService, LoadFileService loadFileCargoService) {
        this.viewFileCargoService = viewFileCargoService;
        this.loadFileCargoService = loadFileCargoService;
    }

    private static final Logger LOGGER = Logger.getLogger(ShellConsoleController.class.getName());

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load_file(
            @ShellOption String fileName,
            @ShellOption String algorithm,
            @ShellOption String countCars
    ) {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return loadFileCargoService.runService(
                        new ServiceRequest(
                                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                                        fileName + " " +
                                        algorithm + " " +
                                        countCars
                        )
                )
                .toString();
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view_file(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return viewFileCargoService.runService(
                        new ServiceRequest(
                                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                                        fileName
                        )
                )
                .toString();
    }
}
