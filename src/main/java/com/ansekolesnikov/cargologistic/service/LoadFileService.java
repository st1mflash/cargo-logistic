package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import com.ansekolesnikov.cargologistic.entity.LoaderPackToCar;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_input.LoadFileServiceRequest;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.LoadFileServiceOutput;
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
public class LoadFileService implements RunnableService {
    private final PackModelDao packModelDao;
    private final CarModelDao carModelDao;
    private final LoaderPackToCar loaderPackToCar;
    private final String PATH_IMPORT_PACKAGE;
    private static final Logger LOGGER = Logger.getLogger(LoadFileService.class.getName());

    public LoadFileService(
            PackModelDao packModelDao,
            CarModelDao carModelDao,
            LoaderPackToCar loaderPackToCar,
            @Value("${directory.pack.import}") String pathImportPackage
    ) {
        this.packModelDao = packModelDao;
        this.carModelDao = carModelDao;
        this.loaderPackToCar = loaderPackToCar;
        this.PATH_IMPORT_PACKAGE = pathImportPackage;
    }

    @Override
    public ServiceOutput runService(ServiceRequest serviceRequest) {
        LoadFileServiceOutput result = new LoadFileServiceOutput();
        try {
            LoadFileServiceRequest command = serviceRequest.getLoadFileServiceInput();
            LocalFile file = new LocalFile(
                    PATH_IMPORT_PACKAGE + command.getFileName()
            );
            AlgorithmEnum algorithm = command.getAlgorithm();
            int countCars = command.getCountCars();

            LoadFileServiceValidation validation = new LoadFileServiceValidation(
                    file,
                    algorithm,
                    countCars
            );

            if (validation.isValid()) {
                List<Pack> importedPackList =
                        importPacksFromFileSortedByWidth(
                                packModelDao,
                                file
                        );
                List<Car> loadedCarList =
                        loadCars(
                                importedPackList,
                                countCars,
                                algorithm
                        );

                if (validation.isValidCountCars(loadedCarList)) {
                    result.setText(toStringCarsInfo(loadedCarList));
                } else {
                    result.setText(validation.getUserErrorMessage());
                }
            } else {
                result.setText(validation.getUserErrorMessage());
            }
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды.");
            result.setText("Ошибка ввода.");
            return result;
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

    public List<Pack> importPacksFromFileSortedByWidth(PackModelDao packModelDao, LocalFile localFile) {
        return Objects
                .requireNonNull(localFile.importPacksFromFile(packModelDao))
                .stream()
                .sorted(Comparator.comparingInt(Pack::getWidth).reversed())
                .toList();
    }

    public List<Car> loadCars(List<Pack> packList, int countCars, AlgorithmEnum algorithm) {
        CarModelEntity defaultCarModelEntity = CarModelEntity.to(carModelDao.findById(1));
        int localCarCount = countCars;
        List<Car> listCars = new ArrayList<>();
        do {
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
