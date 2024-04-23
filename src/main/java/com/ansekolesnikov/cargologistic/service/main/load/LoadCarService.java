package com.ansekolesnikov.cargologistic.service.main.load;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfo;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.service.main.CargoService;
import com.ansekolesnikov.cargologistic.validation.service.LoadCarServiceValidation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoadCarService implements CargoService {
    private static final Logger LOGGER = Logger.getLogger(LoadCarService.class.getName());
    private static final String PATH_IMPORT_PACKAGES = "src/main/resources/import/packages/";
    private LocalFile localFile;
    private String algorithm;
    private int countCars;

    public LoadCarService() {

    }

    @Override
    public String runService(String inputFileName, String inputAlgorithm, String inputCountCars) {
        initParams(inputFileName, inputAlgorithm, inputCountCars);
        LoadCarServiceValidation serviceValidation = new LoadCarServiceValidation(localFile, algorithm, countCars);

        if (serviceValidation.isValid()) {
            List<Car> loadedCarList = loadCarsFromFile();
            if (serviceValidation.isValidCountCars(loadedCarList)) {
                return getCarsInfo(loadedCarList);
            } else {
                return serviceValidation.getUserErrorMessage();
            }
        } else {
            return serviceValidation.getUserErrorMessage();
        }
    }

    private void initParams(String fileName, String algorithm, String countCars) {
        this.localFile = new LocalFile(PATH_IMPORT_PACKAGES + fileName);
        this.algorithm = algorithm.toLowerCase();
        this.countCars = Integer.parseInt(countCars);
    }

    private List<Car> loadCarsFromFile() {
        List<Pack> packList = Objects.requireNonNull(new LocalFileImportUtils().importPacksFromFile(localFile))
                .stream()
                .sorted(Comparator.comparingInt(Pack::getWidth).reversed())
                .toList();

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

    private String getCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(new CarStringInfo().getCargo(car)).append("\n");
            }
        }
        return result.toString();
    }
}
