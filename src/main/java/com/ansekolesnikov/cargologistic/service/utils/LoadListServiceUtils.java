package com.ansekolesnikov.cargologistic.service.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.entity.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.entity.utils.CarUtils;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
public class LoadListServiceUtils {
    @Autowired
    private CarUtils carUtils;
    @Autowired
    private CarToStringUtils carToStringUtils;
    public List<Car> loadCars(CarModel inputCarModel, List<Pack> inputPack, int inputCountCars, AlgorithmEnum inputAlgorithm) {
        List<Car> listCars = new ArrayList<>();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = new Car(inputCarModel);
            listCars.add(car);

            List<Pack> filteredPackList = inputPack.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                carUtils.loadPackToCar(car, pack, inputAlgorithm);
            }
        }
        return listCars;
    }

    public String toStringCarsPacksInfo(List<Pack> pack, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (PackModel packModel : pack) {
            result.append(packModel.getCode()).append(" -- ").append(packModel.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(carToStringUtils.toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }

    public List<Pack> createPacksByNameFromDatabase(
            PackModelDao packModelDao,
            String[] packNames
    ) {
        return Arrays
                .stream(packNames)
                .map(packModelDao::findByName)
                .map(Pack::new)
                .toList();
    }
}
