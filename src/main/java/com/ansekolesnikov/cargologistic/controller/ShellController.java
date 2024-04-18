package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellController {
    private static final Logger LOGGER = Logger.getLogger(ShellController.class.getName());

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load(@ShellOption String fileName, @ShellOption String algorithm, @ShellOption String countCars) {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return new CargoLoadService(fileName, algorithm, countCars).runService();
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return new CargoViewService(fileName).runService();
    }
}
