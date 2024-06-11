package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.*;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadListService implements IRunnableByStringService {
    private final CarModelDao carModelDao;
    private final PackModelDao packModelDao;
    private final LoaderPackToCar loaderPackToCar;

    private static final Logger LOGGER = Logger.getLogger(LoadListService.class.getName());

    @Override
    public String runByStringService(String request) {
        try {
            CarModelEntity carModelEntity = carModelDao.findByName(request.split(" ")[1]);
            List<Pack> packs =
                    createPacksByNameFromDatabase(
                            packModelDao,
                            pullPacksNameListFromString(request)
                    );

            return toStringCarsPacksInfo(
                    packs,
                    loadCars(
                            carModelEntity,
                            packs,
                            Integer.parseInt(request.split(" ")[3]),
                            AlgorithmEnum.initEnumFromString(request.split(" ")[2])
                    )
            );
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды.");
            return "Ошибка ввода.";
        }
    }

    private List<Car> loadCars(
            CarModelEntity inputCarModelEntity,
            List<Pack> inputPack,
            int inputCountCars,
            AlgorithmEnum inputAlgorithm
    ) {
        List<Car> listCars = new ArrayList<>();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = new Car(inputCarModelEntity);
            listCars.add(car);

            List<Pack> filteredPackList = inputPack.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                loaderPackToCar.loadPackToCar(car, pack, inputAlgorithm);
            }
        }
        return listCars;
    }

    private String toStringCarsPacksInfo(List<Pack> pack, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (PackModelEntity packModelEntity : pack) {
            result.append(packModelEntity.getCode()).append(" -- ").append(packModelEntity.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(car.toStringCarCargoScheme()).append("\n");
            }
        }
        return result.toString();
    }

    private List<Pack> createPacksByNameFromDatabase(
            PackModelDao packModelDao,
            String[] packNames
    ) {
        return Arrays
                .stream(packNames)
                .map(packModelDao::findByName)
                .map(PackModelEntity::to)
                .map(Pack::new)
                .toList();
    }

    private String[] pullPacksNameListFromString(String request) {
        return request
                .substring(request.indexOf(":") + 1)
                .trim()
                .split("\\n");
    }
}
