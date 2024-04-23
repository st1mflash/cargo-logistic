package com.ansekolesnikov.cargologistic.service.main.load;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfoUtils;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LoadCarServiceUtils {
    private static final Logger LOGGER = Logger.getLogger(LoadCarServiceUtils.class.getName());

    public String getCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(new CarStringInfoUtils().getCargo(car)).append("\n");
            }
        }
        return result.toString();
    }

    public List<Pack> getListPacksFromFile(LocalFile localFile) {
        return Objects.requireNonNull(new LocalFileImportUtils().importPacksFromFile(localFile))
                .stream()
                .sorted(Comparator.comparingInt(Pack::getWidth).reversed())
                .toList();
    }

    public List<Car> loadCars(List<Pack> packList, int countCars, String algorithm) {
        int localCarCount = countCars;
        List<Car> listCars = new ArrayList<>();
        do {
            Car car = new Car();
            listCars.add(car);
            packList = packList.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .collect(Collectors.toList());

            for (Pack pack : packList) {
                car.loadPack(pack, algorithm);
            }
            if (localCarCount > 0) {
                if (new CarUtils().calcPercentLoad(car) == 0) {
                    LOGGER.info("Грузовик #" + car.getId() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + car.getId() + " успешно загружен на " + new CarUtils().calcPercentLoad(car) + "%");
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
