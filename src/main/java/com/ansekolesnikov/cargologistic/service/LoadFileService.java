package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.entity.*;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapperImpl;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.CarModelRepository;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.selector.LoaderPackToCar;
import com.ansekolesnikov.cargologistic.validation.LoadFileServiceValidation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Setter
@Service
public class LoadFileService implements IRunnableByStringService {
    private LocalFileMapper localFileMapper = new LocalFileMapperImpl();
    @Value("${directory.pack.import}")
    private String PATH_IMPORT_PACKAGE;
    private final PackModelRepository packModelRepository;
    private final CarModelRepository carModelRepository;
    private final LoaderPackToCar loaderPackToCar;
    private final CarModelMapper carModelMapper;
    private final PackModelMapper packModelMapper;
    private final LoadFileServiceValidation loadFileServiceValidation;
    private static final Logger LOGGER = Logger.getLogger(LoadFileService.class.getName());

    @Override
    public String run(RequestString request) {
        try {
            LocalFile file = localFileMapper.toLocalFile(PATH_IMPORT_PACKAGE + request.getFileName());
            AlgorithmEnum algorithm = request.getAlgorithm();
            int countCars = request.getCountCars();

            if (loadFileServiceValidation.isValid(file, algorithm, countCars)) {
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

                if (loadFileServiceValidation.isValidCountCars(loadedCarList, countCars)) {
                    return toStringCarsInfo(loadedCarList);
                } else {
                    return loadFileServiceValidation.getUserErrorMessage();
                }
            } else {
                return loadFileServiceValidation.getUserErrorMessage();
            }
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды: " + e);
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
                .requireNonNull(localFile.importPacksFromFile(packModelRepository, packModelMapper))
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
            Car car = carModelMapper.toCar(defaultCarModelEntity);
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
