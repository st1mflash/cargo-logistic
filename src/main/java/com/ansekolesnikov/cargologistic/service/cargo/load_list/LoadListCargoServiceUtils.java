package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadListCargoServiceUtils {
    public List<Car> loadCars(CarModel inputCarModel, List<PackModel> inputPackModels, int inputCountCars, String inputAlgorithm) {
        List<Car> listCars = new ArrayList<>();
        CarUtils carUtils = new CarUtils();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = new Car(inputCarModel);
            listCars.add(car);

            List<PackModel> filteredPackListModel = inputPackModels.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (PackModel packModel : filteredPackListModel) {
                carUtils.loadPackToCar(car, packModel, inputAlgorithm);
            }
        }
        return listCars;
    }

    public String toStringCarsPacksInfo(List<PackModel> packModels, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (PackModel packModel : packModels) {
            result.append(packModel.getCode()).append(" -- ").append(packModel.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(new CarToStringUtils().toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }

    /*
    public CarModel createCarModelByNameFromDatabase(
            DatabaseService databaseService,
            String name
    ) {
        return car;
    }
    */

    public List<PackModel> createPacksByNameFromDatabase(
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
