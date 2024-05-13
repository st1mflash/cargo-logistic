package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadListCargoServiceUtils {
    public List<Car> loadCars(CarModel inputCarModel, List<Pack> inputPacks, int inputCountCars, String inputAlgorithm) {
        List<Car> listCars = new ArrayList<>();
        CarUtils carUtils = new CarUtils();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = new Car(inputCarModel);
            listCars.add(car);

            List<Pack> filteredPackList = inputPacks.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                carUtils.loadPackToCar(car, pack, inputAlgorithm);
            }
        }
        return listCars;
    }

    public String toStringCarsPacksInfo(List<Pack> packs, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (Pack pack: packs) {
            result.append(pack.getCode()).append(" -- ").append(pack.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(new CarToStringUtils().toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }

    public CarModel createCarModelByNameFromDatabase(
            DatabaseService databaseService,
            String name
    ) {
        return databaseService
                .getOperationsDatabase()
                .getCarOperations()
                .queryByName(name);
    }

    public List<Pack> createPacksByNameFromDatabase(
            DatabaseService databaseService,
            String[] packNames
    ) {
        return Arrays
                .stream(packNames)
                .map(p -> databaseService
                        .getOperationsDatabase()
                        .getPackOperations()
                        .queryByName(p)
                )
                .toList();
    }
}
