package com.ansekolesnikov.cargologistic.controller.shell;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.load.file.LoadFileService;
import com.ansekolesnikov.cargologistic.service.main.view.ViewFileService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@NoArgsConstructor
@ShellComponent
public class ShellController {
    @Autowired
    private ViewFileService viewFileCargoService;
    @Autowired
    private LoadFileService loadFileCargoService;

    private static final Logger LOGGER = Logger.getLogger(ShellController.class.getName());

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
                )
                .getResultLoadFileServiceRun()
                .getStringResult();
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view_file(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return viewFileCargoService.runService(
                        new CommandLine(
                                Thread.currentThread().getStackTrace()[1].getMethodName() + " " +
                                        fileName
                        )
                )
                .getResultViewFileServiceRun()
                .getStringResult();
    }
}
