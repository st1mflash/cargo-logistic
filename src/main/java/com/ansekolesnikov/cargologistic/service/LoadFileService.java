package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.entity.*;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.repository.CarModelRepository;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.validation.LoadFileServiceValidation;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Service
public class LoadFileService implements IRunnableByStringService {
    private final PackModelRepository packModelRepository;
    private final CarModelRepository carModelRepository;
    private final LoaderPackToCar loaderPackToCar;
    private final String PATH_IMPORT_PACKAGE;
    private static final Logger LOGGER = Logger.getLogger(LoadFileService.class.getName());

    public LoadFileService(
            PackModelRepository packModelRepository,
            CarModelRepository carModelRepository,
            LoaderPackToCar loaderPackToCar,
            @Value("${directory.pack.import}") String pathImportPackage
    ) {
        this.packModelRepository = packModelRepository;
        this.carModelRepository = carModelRepository;
        this.loaderPackToCar = loaderPackToCar;
        this.PATH_IMPORT_PACKAGE = pathImportPackage;
    }

    @Override
    public String run(RequestRunnableService request) {
        try {
            LocalFile file = new LocalFile(
                    PATH_IMPORT_PACKAGE + request.getFileName()
            );
            AlgorithmEnum algorithm = request.getAlgorithm();
            int countCars = request.getCountCars();

            LoadFileServiceValidation validation = new LoadFileServiceValidation(
                    file,
                    algorithm,
                    countCars
            );

            if (validation.isValid()) {
                List<Pack> importedPackList =
                        importPacksFromFileSortedByWidth(
                                file
                        );
                List<Car> loadedCarList =
                        loadCars(
                                importedPackList,
                                countCars,
                                algorithm
                        );

                if (validation.isValidCountCars(loadedCarList)) {
                    return toStringCarsInfo(loadedCarList);
                } else {
                    return validation.getUserErrorMessage();
                }
            } else {
                return validation.getUserErrorMessage();
            }
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды.");
            return "Ошибка ввода.";
        }
    }

    public String toStringCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(car.toStringCarCargoScheme()).append("\n");
            }
        }
        return result.toString();
    }

    public List<Pack> importPacksFromFileSortedByWidth(LocalFile localFile) {
        return Objects
                .requireNonNull(localFile.importPacksFromFile(packModelRepository))
                .stream()
                .sorted(Comparator.comparingInt(Pack::getWidth).reversed())
                .toList();
    }

    public List<Car> loadCars(List<Pack> packList, int countCars, AlgorithmEnum algorithm) {
        CarModelEntity defaultCarModelEntity = carModelRepository.findById(1).orElse(null);
        int localCarCount = countCars;
        List<Car> listCars = new ArrayList<>();
        do {
            assert defaultCarModelEntity != null;
            Car car = new Car(defaultCarModelEntity);
            listCars.add(car);
            packList = packList.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .collect(Collectors.toList());

            for (Pack pack : packList) {
                loaderPackToCar.loadPackToCar(car, pack, algorithm);
            }
            if (localCarCount > 0) {
                if (car.calcPercentLoad() == 0) {
                    LOGGER.info("Грузовик #" + car.getId() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + car.getId() + " успешно загружен на " + car.calcPercentLoad() + "%");
                }
            }
            localCarCount--;
        } while (
                packList.stream()
                        .anyMatch(pack -> pack.getCarId() == 0)
                        || localCarCount > 0
        );
        return listCars;
    }
}
