package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoadListCargoServiceUtils {
    public List<Car> loadCars(CarModel inputCarModel, List<Pack> inputPacks, int inputCountCars, String inputAlgorithm) {
        CarModel carModel = inputCarModel;
        List<Pack> packs = inputPacks;
        int amountCars = inputCountCars;
        String algorithm = inputAlgorithm;

        List<Car> listCars = new ArrayList<>();
        CarUtils carUtils = new CarUtils();

        for (int i = 0; i < amountCars; i++) {
            Car car = new Car(carModel);
            listCars.add(car);

            List<Pack> filteredPackList = packs.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                carUtils.loadPackToCar(car, pack, algorithm);
            }
        }
        return listCars;
    }

    public String toStringCarsInfo(List<Car> listCars) {
        StringBuilder result = new StringBuilder();
        if (listCars != null) {
            for (Car car : listCars) {
                result.append(new CarToStringUtils().toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }
}
