package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.exception.CheckCargoException;
import com.ansekolesnikov.cargologistic.exception.LoadCargoException;
import com.ansekolesnikov.cargologistic.model.Cargo;
import com.ansekolesnikov.cargologistic.model.File;
import com.ansekolesnikov.cargologistic.model.Package;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class Controller {
    Logger logger = Logger.getLogger(Controller.class.getName());

    @ShellMethod("Load cargo from file.")
    public void load(@ShellOption String fileName, @ShellOption String algorithm, @ShellOption String countCars) throws Exception {
        fileName = File.convertFileNameToTxtExtension(fileName);
        algorithm = algorithm.toLowerCase();

        logger.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm + "' в " + countCars + " ед. транспорта.");

        LoadCargoException.algorithmExistException(algorithm);
        LoadCargoException.fileExistException(fileName);

        List<Package> listPackages = File.getPackagesFromFile(fileName);
        listPackages = Package.sortListDesc(listPackages);

        List<Cargo> listCargo = Cargo.loadListCargo(listPackages, algorithm, Integer.parseInt(countCars));
        if ((long) listCargo.size() > Integer.parseInt(countCars)) {
            System.out.println("Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + listCargo.size() + "!");
            logger.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + listCargo.size() + ", а указано " + countCars);
        } else {
            Cargo.printListCargo(listCargo);

            logger.info("Загрузка из файла '" + fileName + "' прошла успешно!");
            File.exportListCargoToJsonFile(listCargo);
        }
    }

    @ShellMethod("Check cargo from file")
    public void check(@ShellOption String fileName) throws Exception {
        fileName = File.convertFileNameToJsonExtension(fileName);
        logger.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");

        CheckCargoException.fileExistException(fileName);

        List<Cargo> listCargo = File.importListCargoFromJsonFile(fileName);
        for (Cargo cargo : listCargo) {
            cargo.printCargoFullInfo();
        }
        logger.info("Загрузка из файла '" + fileName + "' прошла успешно! Получена информация о " + (long) listCargo.size() + " грузовике(ах)");
    }
}
