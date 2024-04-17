package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.model.file.CargoTxtFile;
import com.ansekolesnikov.cargologistic.service.ViewService;
import com.ansekolesnikov.cargologistic.validation.LoadCargoException;
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
    //private static final String path

    @ShellMethod("Load cargo from file.")
    public void load(@ShellOption String fileName, @ShellOption String algorithm, @ShellOption String countCars) throws Exception {
        fileName = CargoTxtFile.convertFileNameToTxtExtension(fileName);
        algorithm = algorithm.toLowerCase();

        LOGGER.info("Запрос загрузки из файла '" + fileName + "' алгоритмом '" + algorithm + "' в " + countCars + " ед. транспорта.");

        LoadCargoException.algorithmExistException(algorithm);
        LoadCargoException.fileExistException(fileName);

        List<CargoPackage> listCargoPackages = CargoTxtFile.getPackagesFromFile(fileName);
        listCargoPackages = CargoPackage.sortListDesc(listCargoPackages);

        List<CargoCar> listCargoCars = CargoCar.loadListCargo(listCargoPackages, algorithm, Integer.parseInt(countCars));
        if ((long) listCargoCars.size() > Integer.parseInt(countCars)) {
            System.out.println("Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + listCargoCars.size() + "!");
            LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + listCargoCars.size() + ", а указано " + countCars);
        } else {
            CargoCar.printListCargo(listCargoCars);

            LOGGER.info("Загрузка из файла '" + fileName + "' прошла успешно!");
            CargoTxtFile.exportListCargoToJsonFile(listCargoCars);
        }
    }

    @ShellMethod("Check cargo from file")
    public String view(@ShellOption String fileName) throws Exception {
        LOGGER.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");
        return ViewService.checkService(fileName);


        /*
        //fileName = CargoJsonFile.convertFileNameToJsonExtension(fileName);
        //logger.info("Запрос отображения информации о грузовиках из файла '" + fileName + "'");

        CargoFile file = new CargoFile("src/main/resources/import/cargo/" + fileName);
        //file.getContent();
        //new CargoFile("src/main/resources/import/cargo/" + fileName);

        FileValidation1.fileExistException(fileName);

        List<Cargo> listCargo = CargoTxtFile.importListCargoFromJsonFile(fileName);
        for (Cargo cargo : listCargo) {
            cargo.printCargoFullInfo();
        }
        logger.info("Загрузка из файла '" + fileName + "' прошла успешно! Получена информация о " + (long) listCargo.size() + " грузовике(ах)");

         */
    }
}
