package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.CargoAlgorithm;
import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoFile;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.utils.CargoFileImportUtils;
import com.ansekolesnikov.cargologistic.validation.AlgorithmValidation;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CargoLoadService implements CargoService {
    private static final Logger LOGGER = Logger.getLogger(CargoLoadService.class.getName());
    private static final String PATH_IMPORT_PACKAGES = "src/main/resources/import/packages/";
    private CargoFile cargoFile;
    private String algorithm;
    private int countCars;

    public CargoLoadService() {
        LOGGER.info("Сервис формирования поставки грузами из файла - успешно запущен!");
    }

    @Override
    public String runService(String inputFileName, String inputAlgorithm, String inputCountCars) {
        initParams(inputFileName, inputAlgorithm, inputCountCars);

        FileValidation fileValidation = new FileValidation(cargoFile);
        AlgorithmValidation algorithmValidation = new AlgorithmValidation(algorithm);

        if (!fileValidation.isValid()) {
            LOGGER.error(fileValidation.getLogErrorMessage());
            return fileValidation.getUserErrorMessage();
        } else if (!algorithmValidation.isValid()) {
            LOGGER.error(algorithmValidation.getLogErrorMessage());
            return algorithmValidation.getUserErrorMessage();
        } else {
            List<CargoCar> cargoCarList = createLoadedCars();
            if (cargoCarList.size() > countCars) {
                LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + cargoCarList.size() + ", а указано " + countCars);
                return "Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + cargoCarList.size() + "!";
            } else {
                return getCarsInfo(cargoCarList);
            }
        }
    }

    private void initParams(String fileName, String algorithm, String countCars) {
        this.cargoFile = new CargoFile(PATH_IMPORT_PACKAGES + fileName);
        this.algorithm = algorithm.toLowerCase();
        this.countCars = Integer.parseInt(countCars);
    }

    private List<CargoCar> loadCars(List<CargoPackage> listCargoPackages) {
        int localCarCount = countCars;
        List<CargoCar> listCargoCars = new ArrayList<>();
        do {
            CargoCar cargoCar = new CargoCar();
            listCargoCars.add(cargoCar);
            listCargoPackages = listCargoPackages.stream()
                    .filter(pack -> pack.getIdCargo() == 0)
                    .collect(Collectors.toList());

            for (CargoPackage pack : listCargoPackages) {
                CargoAlgorithm.load(algorithm, cargoCar, pack);
            }
            if (localCarCount > 0) {
                if (cargoCar.getLoadPercent() == 0) {
                    LOGGER.info("Грузовик #" + cargoCar.getId() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + cargoCar.getId() + " успешно загружен на " + cargoCar.getLoadPercent() + "%");
                }
            }
            localCarCount--;

        } while (
                listCargoPackages.stream()
                        .anyMatch(pack -> pack.getIdCargo() == 0)
                        || localCarCount > 0
        );
        return listCargoCars;
    }

    private List<CargoCar> createLoadedCars() {
        List<CargoPackage> cargoPackageList = Objects.requireNonNull(CargoFileImportUtils.importPackagesFromFile(cargoFile))
                .stream()
                .sorted(Comparator.comparingInt(CargoPackage::getWidth).reversed())
                .toList();
        return loadCars(cargoPackageList);
    }

    private String getCarsInfo(List<CargoCar> listCargoCars) {
        StringBuilder result = new StringBuilder();
        if (listCargoCars != null) {
            for (CargoCar cargoCar : listCargoCars) {
                result.append(cargoCar.getCargoScheme()).append("\n");
            }
        }
        return result.toString();
    }
}
