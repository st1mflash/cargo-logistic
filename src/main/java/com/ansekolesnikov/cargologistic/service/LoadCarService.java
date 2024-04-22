package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.load_car.LoadCar;
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
public class LoadCarService implements CargoService {
    private static final Logger LOGGER = Logger.getLogger(LoadCarService.class.getName());
    private static final String PATH_IMPORT_PACKAGES = "src/main/resources/import/packages/";
    private CargoFile cargoFile;
    private String algorithm;
    private int countCars;

    public LoadCarService() {

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
            List<Car> carList = createLoadedCars();
            if (carList.size() > countCars) {
                LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + carList.size() + ", а указано " + countCars);
                return "Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + carList.size() + "!";
            } else {
                return getCarsInfo(carList);
            }
        }
    }

    private void initParams(String fileName, String algorithm, String countCars) {
        this.cargoFile = new CargoFile(PATH_IMPORT_PACKAGES + fileName);
        this.algorithm = algorithm.toLowerCase();
        this.countCars = Integer.parseInt(countCars);
    }

    private List<Car> loadCars(List<CargoPackage> listCargoPackages) {
        int localCarCount = countCars;
        List<Car> listCars = new ArrayList<>();
        do {
            Car car = new Car();
            listCars.add(car);
            listCargoPackages = listCargoPackages.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .collect(Collectors.toList());

            for (CargoPackage cargoPackage : listCargoPackages) {
                new LoadCar().loadPackage(algorithm, car, cargoPackage);
            }
            if (localCarCount > 0) {
                if (car.getLoadPercent() == 0) {
                    LOGGER.info("Грузовик #" + car.getId() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + car.getId() + " успешно загружен на " + car.getLoadPercent() + "%");
                }
            }
            localCarCount--;

        } while (
                listCargoPackages.stream()
                        .anyMatch(pack -> pack.getCarId() == 0)
                        || localCarCount > 0
        );
        return listCars;
    }

    private List<Car> createLoadedCars() {
        List<CargoPackage> cargoPackageList = Objects.requireNonNull(new CargoFileImportUtils().importPackagesFromFile(cargoFile))
                .stream()
                .sorted(Comparator.comparingInt(CargoPackage::getWidth).reversed())
                .toList();
        return loadCars(cargoPackageList);
    }

    private String getCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(car.getCarScheme()).append("\n");
            }
        }
        return result.toString();
    }
}
