package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.model.file.CargoTxtFile;
import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import com.ansekolesnikov.cargologistic.utils.CargoCarUtils;
import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class ShellController {
    private static final Logger LOGGER = Logger.getLogger(ShellController.class.getName());

    @ShellMethod("Формирование поставки грузами из .txt файла.")
    public String load(@ShellOption String fileName, @ShellOption String algorithm, @ShellOption String countCars) throws Exception {
        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm.toLowerCase() + "' в " + countCars + " ед. транспорта.");
        return new CargoLoadService(fileName, algorithm, countCars).runService();
        //LoadCargoException.algorithmExistException(algorithmToLowerCase);
        //LoadCargoException.fileExistException(fileName);
/*
        List<CargoPackage> listCargoPackages = CargoTxtFile.getPackagesFromFile(fileName);
        listCargoPackages = CargoPackage.sortListDesc(listCargoPackages);

        List<CargoCar> listCargoCars = CargoCar.loadListCargo(listCargoPackages, algorithmToLowerCase, Integer.parseInt(countCars));
        if ((long) listCargoCars.size() > Integer.parseInt(countCars)) {
            System.out.println("Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + listCargoCars.size() + "!");
            LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + listCargoCars.size() + ", а указано " + countCars);
        } else {
            CargoCarUtils.printListCargo(listCargoCars);

            LOGGER.info("Загрузка из файла '" + fileName + "' прошла успешно!");
            CargoTxtFile.exportListCargoToJsonFile(listCargoCars);
        }
        */
    }

    @ShellMethod("Получение полной информации о грузовиках из .json файла.")
    public String view(@ShellOption String fileName) {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return new CargoViewService(fileName).runService();
    }
}
