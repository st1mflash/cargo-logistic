package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.load_file.LoadFileCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.view_file.ViewFileCargoService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@NoArgsConstructor
@ShellComponent
public class ShellController {
    private ViewFileCargoService viewFileCargoService;
    private LoadFileCargoService loadFileCargoService;

    private static final Logger LOGGER = Logger.getLogger(ShellController.class.getName());

    public ShellController(
            LoadFileCargoService loadFileCargoService,
            ViewFileCargoService viewFileCargoService
    ) {
        this.loadFileCargoService = loadFileCargoService;
        this.viewFileCargoService = viewFileCargoService;
    }

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load_file(
            @ShellOption String fileName,
            @ShellOption String algorithm,
            @ShellOption String countCars
    ) {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return loadFileCargoService.runService(
                new CommandLine(
                        Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                                fileName + " " +
                                algorithm + " " +
                                countCars
                )
        );
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view_file(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return viewFileCargoService.runService(
                new CommandLine(
                        Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                                fileName
                )
        );
    }
}
