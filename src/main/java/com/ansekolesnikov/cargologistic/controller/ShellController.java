package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellController {
    private static final Logger LOGGER = Logger.getLogger(ShellController.class.getName());
    private final CargoService cargoViewService, cargoLoadService;

    @Autowired
    public ShellController(CargoViewService cargoViewService, CargoLoadService cargoLoadService) {
        this.cargoViewService = cargoViewService;
        this.cargoLoadService = cargoLoadService;
    }

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load(@ShellOption String fileName, @ShellOption String algorithm, @ShellOption String countCars) {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return cargoLoadService.runService(fileName, algorithm, countCars);

    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return cargoViewService.runService(fileName);
    }
}
