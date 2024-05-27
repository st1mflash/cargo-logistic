package com.ansekolesnikov.cargologistic.service.utils;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.CarModel;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.entity.utils.CarUtils;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.utils.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.entity.Pack;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class LoadFileServiceUtils {
    @Autowired
    private CarModelDao carModelDao;
    @Autowired
    private CarUtils carUtils;
    @Autowired
    private CarToStringUtils carToStringUtils;
    @Autowired
    private LocalFileImportUtils localFileImportUtils;
    private static final Logger LOGGER = Logger.getLogger(LoadFileServiceUtils.class.getName());

    public String toStringCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(carToStringUtils.toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }

    public List<Pack> importPacksFromFileSortedByWidth(PackModelDao packModelDao, LocalFile localFile) {
        return Objects
                .requireNonNull(localFileImportUtils.importPacksFromFile(packModelDao, localFile))
                .stream()
                .sorted(Comparator.comparingInt(Pack::getWidth).reversed())
                .toList();
    }

    public List<Car> loadCars(List<Pack> packList, int countCars, AlgorithmEnum algorithm) {
        CarModel defaultCarModel = carModelDao.findById(1);
        int localCarCount = countCars;
        List<Car> listCars = new ArrayList<>();
        do {
            Car car = new Car(defaultCarModel);
            listCars.add(car);
            packList = packList.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .collect(Collectors.toList());

            for (Pack pack : packList) {
                carUtils.loadPackToCar(car, pack, algorithm);
            }
            if (localCarCount > 0) {
                if (carUtils.calcPercentLoad(car) == 0) {
                    LOGGER.info("Грузовик #" + car.getIdModel() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + car.getIdModel() + " успешно загружен на " + carUtils.calcPercentLoad(car) + "%");
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
