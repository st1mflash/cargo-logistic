package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.model.load_car.LoadCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
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
        if(serviceValidation.isValid()) {
            List<Car> carList = createLoadedCars();
            if (carList.size() > countCars) {
                LOGGER.error("Ошибка загрузки: недостаточно машин! Требуется минимум " + carList.size() + ", а указано " + countCars);
                return "Не удалось погрузить все посылки в " + countCars + " ед. транспорта, необходимо " + carList.size() + "!";
            } else {
                return getCarsInfo(carList);
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
        List<CargoPackage> cargoPackageList = Objects.requireNonNull(new LocalFileImportUtils().importPackagesFromFile(localFile))
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
