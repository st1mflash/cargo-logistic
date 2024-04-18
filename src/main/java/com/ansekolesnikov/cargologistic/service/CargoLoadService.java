package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoFile;
import com.ansekolesnikov.cargologistic.model.CargoLoadAlgorithm;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.utils.CargoFileImportUtils;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CargoLoadService {
    private static final Logger LOGGER = Logger.getLogger(CargoLoadService.class.getName());
    private static final String PATH_IMPORT_PACKAGES = "src/main/resources/import/packages/";
    private static final String PATH_EXPORT_CARGO = "src/main/resources/export/cargo/loaded_cargo.json";
    private final CargoFile cargoFile;
    private final String algorithm;
    private final int countCars;

    public CargoLoadService(String fileName, String algorithm, String countCars) {
        this.cargoFile = new CargoFile(PATH_IMPORT_PACKAGES + fileName);
        this.algorithm = algorithm.toLowerCase();
        this.countCars = Integer.parseInt(countCars);
    }

    public String runService() {
        FileValidation fileValidation = new FileValidation(cargoFile);
        if (fileValidation.isValid()) {
            return getCargoCarsInfo(createLoadedCargoCars());
        } else {
            LOGGER.error(fileValidation.getLogErrorMessage());
            return fileValidation.getUserErrorMessage();
        }
    }

    private List<CargoPackage> importPackagesFromFile() {
        try {
            return Arrays.stream(Files.readString(Paths.get(cargoFile.getPathNameFormat())).split("\\n\\s*\\n"))
                    .map(line -> line.charAt(0) - 48)
                    .map(CargoPackage::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }
    private List<CargoCar> loadCargoCars(List<CargoPackage> listCargoPackages) {
        int localCarCount = countCars;
        List<CargoCar> listCargoCars = new ArrayList<>();
        do {
            CargoCar cargoCar = new CargoCar();
            listCargoCars.add(cargoCar);
            listCargoPackages = listCargoPackages.stream()
                    .filter(pack -> pack.getIdCargo() == 0)
                    .collect(Collectors.toList());

            for (CargoPackage pack : listCargoPackages) {
                CargoLoadAlgorithm.load(algorithm, cargoCar, pack);
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
                listCargoPackages
                        .stream()
                        .anyMatch(pack -> pack.getIdCargo() == 0)
                        || localCarCount > 0
        );
        return listCargoCars;
    }
    private List<CargoCar> createLoadedCargoCars() {
        List<CargoPackage> cargoPackageList = Objects.requireNonNull(CargoFileImportUtils.importPackagesFromFile(cargoFile))
                .stream()
                .sorted(Comparator.comparingInt(CargoPackage::getWidth).reversed())
                .toList();
        return loadCargoCars(cargoPackageList);
    }
    private String getCargoCarsInfo(List<CargoCar> listCargoCars){
        StringBuilder result = new StringBuilder();
        if (listCargoCars != null) {
            for (CargoCar cargoCar : listCargoCars) {
                result.append(cargoCar.getCargoScheme()).append("\n");
            }
        }
        return result.toString();
    }
}
